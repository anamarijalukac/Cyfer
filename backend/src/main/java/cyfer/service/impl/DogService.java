package cyfer.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import cyfer.domain.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cyfer.dao.DogRepository;
import cyfer.domain.Dog;
import cyfer.domain.Walk;
import cyfer.service.IDogService;

@Service
public class DogService implements IDogService {

	@Autowired
	private DogRepository dogRepository;

	@Override
	public Dog getDog(long dogId) {
		return dogRepository.findById(dogId).get();
		
	}

	@Override
	public List<Dog> getSheltersDogs(long shelterId) {
		List<Dog> dogs = dogRepository.findAll().stream().filter(d -> d.getShelter().getShelterId() == shelterId).collect(Collectors.toList());
		return dogs;
	}

	@Override
	public List<Dog> getAllDogs() {
		List<Dog> list = new ArrayList<>();
		dogRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public void setDog(Dog dog) {
		dogRepository.save(dog);
	}



	
}
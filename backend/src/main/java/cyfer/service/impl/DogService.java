package cyfer.service.impl;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
		Dog obj = dogRepository.findById(dogId).get();
		return obj;
	}

	@Override
	public List<Dog> getAllDogs() {
		List<Dog> list = new ArrayList<>();
		dogRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Dog getDogByShelterOIB(String shelterOIB) { 
		return null;
	}
	
	@Override
	public void setDog(Dog dog) {
		dogRepository.save(dog);
	}

	
}
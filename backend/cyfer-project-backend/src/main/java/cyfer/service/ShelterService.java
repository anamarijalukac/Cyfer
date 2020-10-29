package cyfer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyfer.dao.PersonRepository;
import cyfer.dao.ShelterRepository;
import cyfer.domain.Person;
import cyfer.domain.Shelter;

@Service
public class ShelterService implements IShelterService {
	
	@Autowired
	private ShelterRepository shelterRepository;
	
	@Override
	public Shelter getShelterByOIB(String shelterOIB) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shelter> getAllShelters() {
		List<Shelter> list = new ArrayList<>();
		shelterRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

}

package cyfer.service.impl;

import java.util.ArrayList; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyfer.dao.ShelterRepository;
import cyfer.domain.Shelter;
import cyfer.service.IShelterService;

@Service
public class ShelterService implements IShelterService {
	
	@Autowired
	private ShelterRepository shelterRepository;
	
	@Override
	public Shelter getShelterByOIB(String OIB) {
		return shelterRepository.findByOIB(OIB);
	}

	@Override
	public List<Shelter> getAllShelters() {
		List<Shelter> list = new ArrayList<>();
		shelterRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	
	@Override
	public Shelter registerShelter(Shelter shelter) {
		return shelterRepository.save(shelter);
	}

	@Override
	public Shelter getShelter(long id) {
		return shelterRepository.findById(id).get();
	}

	@Override
	public Shelter getByUsername(String username) {
		return shelterRepository.findByUsername(username);
	}

}

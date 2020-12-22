package cyfer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyfer.dao.ShelterRepository;
import cyfer.dao.WalkerRepository;
import cyfer.domain.Shelter;
import cyfer.domain.Walker;
import cyfer.service.IWalkerService;

@Service
public class WalkerService implements IWalkerService {

	@Autowired
	private WalkerRepository walkerRepository;
	

	@Override
	public Walker registerWalker(Walker walker) {
		return walkerRepository.save(walker);
	}

	@Override
	public Walker getByUsername(String username) {
		Walker walker = walkerRepository.findByUsername(username);
		return walker;
	}
	
	@Override
	public Walker getByEmail(String email) {
		Walker walker = walkerRepository.findByEmail(email);
		return walker;
	}

	@Override
	public List<Walker> getAllWalkers() {
		List<Walker> list = new ArrayList<>();
		walkerRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Walker getWalker(long id) {
		System.out.println(walkerRepository.findById(id).isPresent());
		return walkerRepository.findById(id).get();
	}

	@Override
	public void delete(Walker walker) {
		walkerRepository.delete(walker);
		
	}

	@Override
	public void deleteWalker(long id) {
		walkerRepository.deleteById(id);
	}


	

	

}

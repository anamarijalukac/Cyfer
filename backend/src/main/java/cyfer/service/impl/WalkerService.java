package cyfer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Autowired
	private ReservationService reservationService;

	@Override
	public Walker registerWalker(Walker walker) {
		walker.setPassword(new BCryptPasswordEncoder().encode(walker.getPassword()));
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

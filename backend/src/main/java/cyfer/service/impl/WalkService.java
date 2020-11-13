package cyfer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyfer.dao.DogRepository;
import cyfer.dao.WalkRepository;
import cyfer.domain.Dog;
import cyfer.domain.Walk;
import cyfer.service.IWalkService;

@Service
public class WalkService implements IWalkService {

	@Autowired
	private WalkRepository walkRepository;



	@Override
	public void setWalk(Walk walk) {
		walkRepository.save(walk);
	}

	@Override
	public List<Walk> getAllWalks() {
		List<Walk> list = new ArrayList<>();
		walkRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Walk getWalk(long walkId) {
		return walkRepository.findById(walkId).get();
		
	}

	
}

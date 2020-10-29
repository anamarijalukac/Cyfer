package cyfer.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyfer.dao.DogRepository;
import cyfer.dao.WalkRepository;
import cyfer.domain.Dog;
import cyfer.domain.Walk;

@Service
public class WalkService implements IWalkService {

	@Autowired
	private WalkRepository walkRepository;

	@Override
	public Walk getWalkByPK(Timestamp walkDate_n_time, String personUsername, String dogId) {
		Walk obj = walkRepository.findByPK(walkDate_n_time, personUsername, dogId);
		return obj;
	}

	@Override
	public void setWalker(Walk walk) {
		walkRepository.save(walk);
	}

	@Override
	public List<Walk> getAllWalks() {
		List<Walk> list = new ArrayList<>();
		walkRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

}

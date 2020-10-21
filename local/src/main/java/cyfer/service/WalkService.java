package cyfer.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import cyfer.domain.Walk;

@Service
public class WalkService implements IWalkService {

	@Override
	public Walk getWalkByPK(Timestamp walkDate_n_time, int walkDuration, String dogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Walk setWalker(String personUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Walk[] getAllWalks() {
		// TODO Auto-generated method stub
		return null;
	}

}

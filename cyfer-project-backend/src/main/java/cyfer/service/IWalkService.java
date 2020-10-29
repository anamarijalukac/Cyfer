package cyfer.service;

import java.sql.Timestamp;
import java.util.List;

import cyfer.domain.Walk;

public interface IWalkService {
	Walk getWalkByPK(Timestamp walkDate_n_time, String personUsername, String dogId);
	void setWalker(Walk walk);
	List<Walk> getAllWalks();
}

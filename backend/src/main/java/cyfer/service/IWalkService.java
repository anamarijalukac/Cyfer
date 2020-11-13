package cyfer.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import cyfer.domain.Walk;

public interface IWalkService {
	
	
	List<Walk> getAllWalks();
	void setWalk(Walk walk);

	Walk getWalk(long walkId);
}

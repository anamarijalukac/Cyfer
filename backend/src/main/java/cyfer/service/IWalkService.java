package cyfer.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import cyfer.domain.Walk;

public interface IWalkService {
	Optional<Walk> findWalkByID(int walkId);
	void setWalker(Walk walk);
	List<Walk> getAllWalks();
}

package cyfer.service;

import java.sql.Timestamp;

import cyfer.domain.Walk;

public interface IWalkService {
	Walk getWalkByPK(Timestamp walkDate_n_time, int walkDuration, String dogId);
	Walk setWalker(String personUsername);
	Walk[] getAllWalks();
}

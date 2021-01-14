package cyfer.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import cyfer.domain.Dog;
import cyfer.domain.Reservation;
import cyfer.domain.Walk;
import cyfer.domain.Walker;

public interface IReservationService {

	Reservation getReservation(long reservationId);

	List<Reservation> getAllReservations();

	Reservation createReservation(Walker walker, Walk walk, Dog dog);

	void deleteReservation(long reservationId);

	List<Dog> getDogsStatistics();

	List<Timestamp> getCalendar(long walkerId);

	Map<String, Integer> getRankListByWalkDuration();

	Map<String, Integer> getRankListByWalkNumber();

	Map<String, Integer> getRankListByDogNumber();

    List<Reservation> getByWalker(Walker walker);

	Map<Walk, List<Reservation>> getByWalkerAndWalk(Walker walker);

	boolean checkAvailable(Walk walk, Dog dog);

}

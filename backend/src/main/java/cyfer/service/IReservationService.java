package cyfer.service;

import java.util.List;

import cyfer.domain.Dog;
import cyfer.domain.Reservation;
import cyfer.domain.Walk;
import cyfer.domain.Walker;

public interface IReservationService {

	Reservation getReservation(long reservationId);

	

	List<Reservation> getAllReservations();

	

	
	void createReservation(Walker walker, Walk walk, Dog dog);

	

}

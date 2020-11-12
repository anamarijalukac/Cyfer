package cyfer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyfer.dao.ReservationRepository;
import cyfer.dao.WalkerRepository;
import cyfer.domain.Dog;
import cyfer.domain.Reservation;
import cyfer.domain.Walk;
import cyfer.domain.Walker;
import cyfer.service.IReservationService;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private WalkerRepository walkerRepository;

	
	@Override
	public Reservation getReservation(long reservationId) {
		return reservationRepository.findById(reservationId).get();
		
	}

	

	@Override
	public List<Reservation> getAllReservations() {
		List<Reservation> list = new ArrayList<>();
		reservationRepository.findAll().forEach(e -> list.add(e));
		return list;
	}



	
	@Override
	public void createReservation(Walker walker,Walk walk, Dog dog) {
		
		Reservation newReservation=new Reservation(walk,walker,dog);
		List<Reservation> list=walker.getReservations();
		list.add(newReservation);
		walker.setReservations(list);
		walkerRepository.save(walker);
		return;
		
	}

	

}

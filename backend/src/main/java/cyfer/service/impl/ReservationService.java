package cyfer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cyfer.dao.DogRepository;
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
	@Autowired
	private DogRepository dogRepository;
	
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
	public Reservation createReservation(Walker walker,Walk walk, Dog dog) {
		
		Reservation newReservation=new Reservation(walk,walker,dog);
		reservationRepository.save(newReservation);
		return newReservation;
		
	}



	@Override
	public List<Dog> getDogsStatistics() {
		List<Dog> list=new ArrayList<>();
		Pageable top = PageRequest.of(0, 3);
		reservationRepository.findDogsStatisticsFromReservation(top).forEach(e->list.add(e));
		return list;
	}

	@Override
	public List<Timestamp> getCalendar(long walkerId) {
		List<Reservation> list = reservationRepository.findAll().stream().filter(r -> r.getWalker().getWalkerId() == walkerId).collect(Collectors.toList());
		List<Timestamp> dates = new LinkedList<>();
		for(Reservation r : list) {
			dates.add(r.getWalk().getDateTime());
		}
		return dates;
	}


}

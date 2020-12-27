package cyfer.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cyfer.dao.WalkRepository;
import org.apache.tomcat.jni.Local;
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
	@Autowired
	private WalkRepository walkRepository;
	
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

	//sortirati silazno!!!
	@Override
	public Map<String, Integer> getRanklistByWalkDuration() {
		Map<String, Integer> walkers = reservationRepository.findAll().stream().filter(r -> LocalDate.now().minusMonths(1).isBefore(r.getWalk().getDateTime().toLocalDateTime().toLocalDate()))
				.collect(Collectors.toMap(r -> r.getWalker().getUsername(), r -> r.getWalk().getDuration(), (w1, w2) -> w1 + w2));
		return walkers;
	}

	//sortirati silazno!!!
	@Override
	public Map<String, Integer> getRankListByDogNumber() {
		Map<String, Integer> map = new HashMap<>();
		for(Reservation r : reservationRepository.findAll()) {
			if(!LocalDate.now().minusMonths(1).isBefore(r.getWalk().getDateTime().toLocalDateTime().toLocalDate())) continue;
			if(map.containsKey(r.getWalker().getUsername())) {
				map.put(r.getWalker().getUsername(), map.get(r.getWalker().getUsername()) + 1);
			} else map.put(r.getWalker().getUsername(), 1);
		}
		return map;
	}

	//sortirati silazno!!!
	@Override
	public Map<String, Integer> getRankListByWalkNumber() {
		Map<String, Integer> map = new HashMap<>();
		List<Reservation> list = reservationRepository.findAll();
		for(Reservation reservation : reservationRepository.findAll()) {
			List<Reservation> listRes = reservationRepository.findByWalkAndWalker(reservation.getWalk(), reservation.getWalker());
			String username = listRes.get(0).getWalker().getUsername();
			if(!LocalDate.now().minusMonths(1).isBefore(reservation.getWalk().getDateTime().toLocalDateTime().toLocalDate())) continue;
			if(map.containsKey(username)) {
				map.put(username, map.get(username) + 1);
			} else map.put(username, 1);
		}
		return map;
	}


}

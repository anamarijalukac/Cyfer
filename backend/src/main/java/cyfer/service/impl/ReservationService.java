package cyfer.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyfer.dao.ReservationRepository;
import cyfer.domain.Dog;
import cyfer.domain.Reservation;
import cyfer.domain.Walk;
import cyfer.domain.Walker;
import cyfer.service.IReservationService;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	
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
	public Reservation createReservation(Walker walker, Walk walk, Dog dog) {
		Reservation newReservation=new Reservation(walk,walker,dog);
		reservationRepository.save(newReservation);
		return newReservation;
	}

	@Override
	public boolean checkAvailable(Walk walk, Dog dog) {
		List<Reservation> reservations = reservationRepository.findByDog(dog);
		for(Reservation reservation : reservations) {
			LocalDateTime begOfWalk = reservation.getWalk().getDateTime().toLocalDateTime();
			LocalDateTime endOfWalk = begOfWalk.plusMinutes(reservation.getWalk().getDuration());
			if((begOfWalk.isBefore(walk.getDateTime().toLocalDateTime()) || begOfWalk.isEqual(walk.getDateTime().toLocalDateTime()))
			&& endOfWalk.isAfter(walk.getDateTime().toLocalDateTime())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void deleteReservation(long reservationId) {
		reservationRepository.deleteById(reservationId);
	}

	@Override
	public List<Dog> getDogsStatistics() {
		Map<Dog,Integer> dogs=new HashMap<>();
		List<Reservation> rez=getAllReservations();
		for(Reservation el:rez){
			Dog dog=el.getDog();
			if(dogs.containsKey(el.getDog()))
				dogs.replace(dog,dogs.get(dog)+el.getDuration());
			else
				dogs.put(dog,el.getDuration());
		}

		List<Dog> list=new ArrayList<>();
		list=dogs.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.map(entry->entry.getKey())
				.limit(3)
				.collect(Collectors.toList());


		return list;
	}

	@Override
	public List<Timestamp> getCalendar(long walkerId) {
		List<Reservation> list = reservationRepository.findAll().stream()
				.filter(r -> r.getWalker().getWalkerId() == walkerId).collect(Collectors.toList());
		List<Timestamp> dates = new LinkedList<>();
		for(Reservation r : list) {
			dates.add(r.getWalk().getDateTime());
		}
		return dates;
	}

	@Override
	public Map<String, Integer> getRankListByWalkDuration() {
		/*Map<String, Integer> walkers = reservationRepository.findAll().stream()
				.filter(r -> LocalDate.now().minusMonths(1)
						.isBefore(r.getWalk().getDateTime().toLocalDateTime().toLocalDate()) && r.getWalker().getStatVisibility())
				.collect(Collectors.toMap(r -> r.getWalker().getUsername(), r -> r.getWalk().getDuration(), (w1, w2) -> w1 + w2));
		*/
		Map<String, Integer> result = new HashMap<>();
		List<List<Reservation>> listForChecking = new ArrayList<>();
		for(Reservation r : reservationRepository.findAll()) {
			if(!LocalDate.now().minusMonths(1).isBefore(r.getWalk().getDateTime().toLocalDateTime().toLocalDate()))
				continue;
			List<Reservation> list = reservationRepository.findByWalkAndWalker(r.getWalk(), r.getWalker());
			if(listForChecking.contains(list)) continue;
			listForChecking.add(list);
			if(result.containsKey(r.getWalker().getUsername()))
				result.put(r.getWalker().getUsername(), result.get(r.getWalker().getUsername()) + r.getWalk().getDuration());
			else
				result.put(r.getWalker().getUsername(), r.getWalk().getDuration());
		}
		return sortByValue(result);
	}

	@Override
	public Map<String, Integer> getRankListByDogNumber() {
		Map<String, Integer> map = new HashMap<>();
		for(Reservation r : reservationRepository.findAll()) {
			if(!LocalDate.now().minusMonths(1).isBefore(r.getWalk().getDateTime().toLocalDateTime().toLocalDate()))
				continue;
			if(!r.getWalker().getStatVisibility())
				continue;
			if(map.containsKey(r.getWalker().getUsername())) {
				map.put(r.getWalker().getUsername(), map.get(r.getWalker().getUsername()) + 1);
			} else map.put(r.getWalker().getUsername(), 1);
		}
		return sortByValue(map);
	}

	@Override
	public Map<Walk, List<Reservation>> getByWalkerAndWalk(Walker walker) {
		Map<Integer, List<Reservation>> mapa = new HashMap<>();
		return reservationRepository.findByWalker(walker).stream()
				//.map(reservation -> { return new reservation.getDog().getName(), reservation.getWalk().getDateTime(), reservation.getDuration() })
		.collect(Collectors.groupingBy(Reservation::getWalk));
	}

	@Override
	public List<Reservation> getByWalker(Walker walker) {
		return reservationRepository.findByWalker(walker);
	}

	@Override
	public Map<String, Integer> getRankListByWalkNumber() {
		Map<String, Integer> map = new HashMap<>();
		List<List<Reservation>> list = new ArrayList<>();

		for(Reservation reservation : reservationRepository.findAll()) {
			if(!LocalDate.now().minusMonths(1).isBefore(reservation.getWalk().getDateTime().toLocalDateTime().toLocalDate())) continue;
			if(!reservation.getWalker().getStatVisibility())
				continue;
			List<Reservation> listRes = reservationRepository.findByWalkAndWalker(reservation.getWalk(), reservation.getWalker());
			if(list.contains(listRes)) continue;
			list.add(listRes);
			String username = listRes.get(0).getWalker().getUsername();
			if(map.containsKey(username)) {
				map.put(username, map.get(username) + 1);
			} else map.put(username, 1);
		}
		return sortByValue(map);
	}

	//util method for sorting map by value (used in rank-lists)
	private static Map<String, Integer> sortByValue(Map<String, Integer> map) {

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list)
			sortedMap.put(entry.getKey(), entry.getValue());

		return sortedMap;

	}


}

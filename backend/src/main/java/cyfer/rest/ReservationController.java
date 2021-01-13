package cyfer.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cyfer.domain.Dog;
import cyfer.domain.Reservation;
import cyfer.domain.Walk;
import cyfer.domain.Walker;
import cyfer.service.IDogService;
import cyfer.service.IReservationService;
import cyfer.service.IWalkService;
import cyfer.service.IWalkerService;

@RestController
@RequestMapping("")
public class ReservationController {

	@Autowired
	private IReservationService reservationService;
	@Autowired
	private IWalkerService walkerService;
	@Autowired
	private IWalkService walkService;
	@Autowired
	private IDogService dogService;

	@PostMapping("/reserve/{dogId}")
	public ResponseEntity<Reservation> createReservation(@PathVariable("dogId") long dogId, @RequestBody Walk walk,
														 @AuthenticationPrincipal User user) {
		if(walk.getDuration() < 0 || walk.getDuration() > 180) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(user == null)
			return new ResponseEntity<Reservation>((Reservation)null,HttpStatus.UNAUTHORIZED);

		Walk newWalk = walkService.setWalk(walk);
		//System.out.println(newWalk.toString());
		Dog dog = dogService.getDog(dogId);
		if(!reservationService.checkAvailable(walk, dog)) return new ResponseEntity<>(HttpStatus.CONFLICT);
		Walker walker = walkerService.getByUsername(user.getUsername());
		Reservation newReservation = reservationService.createReservation(walker, newWalk, dog);
		return new ResponseEntity<Reservation>(newReservation,HttpStatus.OK);
	}

	@PostMapping(path="/reserve/dogs", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Reservation> createGroupReservation(@RequestParam("dog")List<Long> dogIds, @RequestBody Walk walk,
															  @AuthenticationPrincipal User user) {
		if(walk.getDuration() < 0 || walk.getDuration() > 180) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Walk newWalk = walkService.setWalk(walk);
		//System.out.println(newWalk.toString());
		List<Dog> dogs = new ArrayList<>();
		for(long dogId : dogIds) {
			Dog dog = dogService.getDog(dogId);
			if(!reservationService.checkAvailable(walk, dog)) return new ResponseEntity<>(HttpStatus.CONFLICT);
			dogs.add(dog);
		}

		for(Dog dog : dogs) {
			Walker walker = walkerService.getByUsername(user.getUsername());
			Reservation newReservation = reservationService.createReservation(walker, newWalk, dog);
		}
		return new ResponseEntity<>(null,HttpStatus.OK);
	}


	@GetMapping("dog/statistics")
	public ResponseEntity<List<Dog>> getAllDogStatistics() {
		List<Dog> dogs = reservationService.getDogsStatistics();
		return new ResponseEntity<List<Dog>>(dogs, HttpStatus.OK);
	}

	@GetMapping("dog/statistics/other")
	public ResponseEntity<List<Dog>> getAllDogStatisticsOther() {
		List<Dog> dogs = reservationService.getDogsStatistics();
		List<Dog> allDogs = dogService.getAllDogs();
		allDogs.removeAll(dogs);
		return new ResponseEntity<List<Dog>>(allDogs, HttpStatus.OK);
	}

	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation>> getAllReservations() {

		List<Reservation> list = reservationService.getAllReservations();
		return new ResponseEntity<List<Reservation>>(list, HttpStatus.OK);
	}

	@GetMapping("/reservations/{walkerId}")
	@Secured({"ROLE_WALKER"})
	public ResponseEntity<Map<Walk, List<Reservation>>> getUserReservations(@PathVariable("walkerId") long walkerId, @AuthenticationPrincipal User user) {
		if(user.getUsername().equals(walkerService.getWalker(walkerId).getUsername())) {
			Map<Walk, List<Reservation>> list = reservationService.getByWalkerAndWalk(walkerService.getWalker(walkerId));
			return new ResponseEntity<Map<Walk, List<Reservation>>>(list, HttpStatus.OK);
		}
		else return null;
	}

}

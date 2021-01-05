package cyfer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
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

	@PostMapping("/shelter/{shelterId}/{dogId}/reserve")
	public ResponseEntity<Reservation> createReservation(@PathVariable("dogId") long dogId, @RequestBody Walk walk,
														 @AuthenticationPrincipal User user) {
		Walk newWalk = walkService.setWalk(walk);
		//System.out.println(newWalk.toString());
		Dog dog = dogService.getDog(dogId);
		Walker walker = walkerService.getByUsername(user.getUsername());
		Reservation newReservation = reservationService.createReservation(walker, newWalk, dog);
		return new ResponseEntity<Reservation>(newReservation,HttpStatus.OK);
	}

	@GetMapping("dog/statistics")
	public ResponseEntity<List<Dog>> getAllDogStatistics() {
		List<Dog> dogs = reservationService.getDogsStatistics();
		return new ResponseEntity<List<Dog>>(dogs, HttpStatus.OK);
	}

	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation>> getAllReservations() {

		List<Reservation> list = reservationService.getAllReservations();
		return new ResponseEntity<List<Reservation>>(list, HttpStatus.OK);
	}

}

package cyfer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
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
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	private IReservationService reservationService;
	@Autowired
	private IWalkerService walkerService;
	@Autowired
	private IWalkService walkService;
	@Autowired
	private IDogService dogService;
	
	
	
	@PostMapping("")
	public ResponseEntity<Reservation> createReservation(@RequestParam Long walkerId, @RequestParam Long walkId,@RequestParam Long dogId) {
		
		Walker walker=walkerService.getWalker(walkerId);
		Walk walk=walkService.getWalk(walkId);
		Dog dog=dogService.getDog(dogId);
		reservationService.createReservation(walker,walk,dog);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	//////////////izbrisati, ovo je samo demo
	@PostMapping("/proba")
	public ResponseEntity<Reservation> azuriraj(@RequestParam Long stari) {
		
		Walker walker=walkerService.getWalker(stari);
		
		walkerService.delete(walker);

		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("")
	public ResponseEntity<List<Reservation>> getAllReservations() {

		List<Reservation> list = reservationService.getAllReservations();
		return new ResponseEntity<List<Reservation>>(list, HttpStatus.OK);
	}

}

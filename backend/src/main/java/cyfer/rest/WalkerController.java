package cyfer.rest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cyfer.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cyfer.domain.Walker;
import cyfer.service.IWalkerService;

@RestController
@RequestMapping("/walker")
public class WalkerController {

	@Autowired
	private IWalkerService walkerService;

	@Autowired
	private IReservationService reservationService;

	@PostMapping("/login")
	public ResponseEntity<Walker> loginWalker(@RequestBody Walker walker) {
		Walker newWalker = walkerService.getByUsername(walker.getUsername());
		boolean passwordTrue = new BCryptPasswordEncoder().matches(walker.getPassword(),newWalker.getPassword());
		if (passwordTrue) {
			System.out.println("USPJESAN LOGIN!");
			return new ResponseEntity<Walker>(newWalker, HttpStatus.OK);
		} else {
			return new ResponseEntity<Walker>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<Walker> registerWalker(@RequestBody Walker walker,
												 @AuthenticationPrincipal User user) {
		Walker newWalker = walkerService.registerWalker(walker);
		if (newWalker != null)
			return new ResponseEntity<Walker>(newWalker, HttpStatus.OK);
		else
			return new ResponseEntity<Walker>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/update/{id}")
	@Secured("ROLE_WALKER")
	public ResponseEntity<Walker> updateWalkerInfo(@RequestBody Walker walker, @PathVariable("id") long id) {
		if (walkerService.getWalker(id) == null)
			return new ResponseEntity<Walker>(HttpStatus.BAD_REQUEST);
		walker.setWalkerId(id);
		walkerService.registerWalker(walker);
		return new ResponseEntity<Walker>(walker, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Secured({"ROLE_WALKER"})
	public Walker getWalker(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
		if(user.getUsername().equals(walkerService.getWalker(id).getUsername()))
			return walkerService.getWalker(id);
		else return null;
	}

	@GetMapping("")
	@Secured("ROLE_PUBLIC")
	public ResponseEntity<List<Walker>> getAllUsers() {

		List<Walker> list = walkerService.getAllWalkers();
		return new ResponseEntity<List<Walker>>(list, HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	@Secured("ROLE_WALKER")
	public ResponseEntity<HttpStatus> deleteWalker(@PathVariable("id") long id) {
		walkerService.deleteWalker(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@GetMapping("/{id}/calendar")
	@Secured("ROLE_WALKER")
	public List<Timestamp> getWalkSchedule(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
		if(user.getUsername().equals(walkerService.getWalker(id).getUsername()))
			return reservationService.getCalendar(id);
		else
			return null;
	}


}

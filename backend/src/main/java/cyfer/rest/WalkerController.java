package cyfer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cyfer.domain.Walker;
import cyfer.service.IWalkerService;

@RestController
@RequestMapping("/walkers")
public class WalkerController {

	@Autowired
	private IWalkerService walkerService;
	
	@PostMapping("/login")
	public ResponseEntity<Walker> loginWalker(@RequestBody Walker walker) {
		Walker newWalker = walkerService.getByUsername(walker.getUsername());
		System.out.println(newWalker.getUsername() + " " + newWalker.getPassword());
		if(newWalker != null && newWalker.getPassword().equals(walker.getPassword())) {
			System.out.println("USPJESAN LOGIN!");
			return new ResponseEntity<Walker>(newWalker, HttpStatus.OK);
		} else {
			return new ResponseEntity<Walker>(walker, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<Walker> registerWalker(@RequestBody Walker walker) {
		Walker newWalker = walkerService.registerWalker(walker);
		return new ResponseEntity<Walker>(newWalker, HttpStatus.OK);
	}

	
	@GetMapping("/{id}")
	public Walker getUser(@PathVariable("id") long id) {
		return walkerService.getWalker(id);
	}

	@GetMapping("")
	public ResponseEntity<List<Walker>> getAllUsers() {

		List<Walker> list = walkerService.getAllWalkers();
		return new ResponseEntity<List<Walker>>(list, HttpStatus.OK);
	}
}

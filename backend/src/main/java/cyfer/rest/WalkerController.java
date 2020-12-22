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
@RequestMapping("/walker")
public class WalkerController {

	@Autowired
	private IWalkerService walkerService;

	@PostMapping("/login")
	public ResponseEntity<Walker> loginWalker(@RequestBody Walker walker) {
		Walker newWalker = walkerService.getByUsername(walker.getUsername());
		if (newWalker != null && newWalker.getPassword().equals(walker.getPassword())) {
			System.out.println("USPJESAN LOGIN!");
			return new ResponseEntity<Walker>(newWalker, HttpStatus.OK);
		} else {
			return new ResponseEntity<Walker>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<Walker> registerWalker(@RequestBody Walker walker) {
		Walker newWalker = walkerService.registerWalker(walker);
		System.out.println(newWalker);
		System.out.println(newWalker.getUsername());
		if (newWalker != null)
			return new ResponseEntity<Walker>(newWalker, HttpStatus.OK);
		else
			return new ResponseEntity<Walker>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<Walker> updateWalkerInfo(@RequestBody Walker walker) {
		if (walkerService.getByUsername(walker.getUsername()) != null
				&& walkerService.getByEmail(walker.getEmail()) != null)
			return new ResponseEntity<Walker>(HttpStatus.BAD_REQUEST);
		walkerService.registerWalker(walker);
		return new ResponseEntity<Walker>(walker, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public Walker getWalker(@PathVariable("id") long id) {
		return walkerService.getWalker(id);
	}

	@GetMapping("")
	public ResponseEntity<List<Walker>> getAllUsers() {

		List<Walker> list = walkerService.getAllWalkers();
		return new ResponseEntity<List<Walker>>(list, HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteWalker(@PathVariable("id") long id) {
		walkerService.deleteWalker(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}

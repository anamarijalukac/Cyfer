package cyfer.rest;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cyfer.domain.Dog;
import cyfer.domain.Walk;
import cyfer.service.IDogService;
import cyfer.service.IWalkService;

@RestController
@RequestMapping("")
public class WalkController {

	@Autowired
	private IWalkService walkService;

	@GetMapping("walk")
	public ResponseEntity<List<Walk>> getAllWalks() {
		List<Walk> list = walkService.getAllWalks();
		return new ResponseEntity<List<Walk>>(list, HttpStatus.OK);
	}

	@PostMapping("walk")
	public ResponseEntity<Walk> setWalker(@RequestBody Walk walk) {
		walkService.setWalk(walk);
		return new ResponseEntity<Walk>(walk, HttpStatus.OK);
	}
}
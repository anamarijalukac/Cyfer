package cyfer.rest;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import cyfer.domain.Shelter;
import cyfer.domain.Walker;
import cyfer.service.IShelterService;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
	
	@Autowired
	private IShelterService shelterService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<Shelter> registerShelter(@RequestBody Shelter shelter) {
		Shelter newShelter = shelterService.registerShelter(shelter);
		if(newShelter != null) return new ResponseEntity<Shelter>(newShelter, HttpStatus.OK);
		else return new ResponseEntity<Shelter>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Shelter> loginShelter(@RequestBody Shelter shelter) {
		Shelter newShelter = shelterService.getByUsername(shelter.getUsername());
		if(newShelter != null && newShelter.getPassword().equals(shelter.getPassword())) {
			System.out.println("USPJESAN LOGIN!");
			return new ResponseEntity<Shelter>(newShelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<Shelter>(shelter, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public Shelter getShelter(@PathVariable("id") long id) {
		return shelterService.getShelter(id);
	}
	
	
	@GetMapping("")
	public ResponseEntity<List<Shelter>> getAllShelter() {
		
		List<Shelter> list = shelterService.getAllShelters();
		return new ResponseEntity<List<Shelter>>(list, HttpStatus.OK);
	}
}

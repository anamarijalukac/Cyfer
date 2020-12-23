package cyfer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import cyfer.domain.Dog;
import cyfer.domain.Shelter;
import cyfer.domain.Walker;
import cyfer.service.IDogService;
import cyfer.service.IShelterService;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

	@Autowired
	private IShelterService shelterService;
	@Autowired
	private IDogService dogService;

	@PostMapping("/signup")
	public ResponseEntity<Shelter> registerShelter(@RequestBody Shelter shelter) {
		String password = shelter.getPassword();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passEncoded = encoder.encode(password);
		shelter.setPassword(passEncoded);
		Shelter newShelter = shelterService.registerShelter(shelter);
		if (newShelter != null)
			return new ResponseEntity<Shelter>(newShelter, HttpStatus.OK);
		else
			return new ResponseEntity<Shelter>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public ResponseEntity<Shelter> loginShelter(@RequestBody Shelter shelter) {
		Shelter newShelter = shelterService.getByUsername(shelter.getUsername());
		boolean passwordTrue = new BCryptPasswordEncoder().matches(shelter.getPassword(),shelter.getPassword());
		//System.out.println(newShelter.getShelterId() + " " + newShelter.getUsername() + " " + shelter.getShelterId());
		if (passwordTrue) {
			System.out.println("USPJESAN LOGIN!");
			return new ResponseEntity<Shelter>(newShelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<Shelter>(shelter, HttpStatus.BAD_REQUEST);
		}
	}

	
	@GetMapping("/{id}")
	@Secured("ROLE_SHELTER")
	public Shelter getShelter(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
		if(user.getUsername().equals(shelterService.getShelter(id).getUsername()))
			return shelterService.getShelter(id);
		else return null;
	}
	
	@GetMapping("/name/{name}")
	public Shelter getShelterByName(@PathVariable("name") String name) {
		return shelterService.getByUsername(name);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Shelter>> getAllShelter() {
		List<Shelter> list = shelterService.getAllShelters();
		return new ResponseEntity<List<Shelter>>(list, HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	@Secured("ROLE_SHELTER")
	public ResponseEntity<HttpStatus> deleteShelter(@PathVariable("id") long id) {
		shelterService.deleteShelter(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@PostMapping("/{shelterId}/add/{dogId}")
	@Secured("ROLE_SHELTER")
	public ResponseEntity<HttpStatus> addDogToShelter(@PathVariable("shelterId") long shelterId,@PathVariable("dogId") long dogId) {
		Shelter shelter=shelterService.getShelter(shelterId);
		Dog dog=dogService.getDog(dogId);
		if(dog.getShelter()!=null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		shelterService.addDog(shelter,dog);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}

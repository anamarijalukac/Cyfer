package cyfer.rest;

import cyfer.domain.Location;
import cyfer.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private ILocationService locationService;

	@PostMapping("/signup")
	public ResponseEntity<Shelter> registerShelter(@RequestBody Shelter shelter) {
		Location location = locationService.getByAddressAndCity(shelter.getAddress(), shelter.getCity());
		if(location != null) {
			shelter.setLocation(location);
		} else {
			Location location1 = new Location();
			location1.setAddress(shelter.getAddress());
			location1.setCity(shelter.getCity());
			shelter.setLocation(location1);
		}
		Shelter newShelter = shelterService.registerShelter(shelter);
		if (newShelter != null)
			return new ResponseEntity<Shelter>(newShelter, HttpStatus.OK);
		else
			return new ResponseEntity<Shelter>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public ResponseEntity<Shelter> loginShelter(@RequestBody Shelter shelter) {
		Shelter newShelter = shelterService.getByUsername(shelter.getUsername());
		boolean passwordTrue = new BCryptPasswordEncoder().matches(shelter.getPassword(),newShelter.getPassword());
		if (passwordTrue) {
			System.out.println("USPJESAN LOGIN!");
			return new ResponseEntity<Shelter>(newShelter, HttpStatus.OK);
		} else {
			return new ResponseEntity<Shelter>(shelter, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	@Secured("ROLE_SHELTER")
	public ResponseEntity<Shelter> getShelter(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
		if(user.getUsername().equals(shelterService.getShelter(id).getUsername())) {
			return new ResponseEntity<Shelter>(shelterService.getShelter(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<Shelter>((Shelter) null, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("")
	public ResponseEntity<List<Shelter>> getAllShelters() {
		List<Shelter> list = shelterService.getAllShelters();
		return new ResponseEntity<List<Shelter>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	@Secured("ROLE_PUBLIC")
	public Shelter getShelterByName(@PathVariable("name") String name) {
		return shelterService.getByUsername(name);
	}

	@PostMapping("/delete/{id}")
	@Secured("ROLE_SHELTER")
	public ResponseEntity<HttpStatus> deleteShelter(@PathVariable("id") long id) {
		shelterService.deleteShelter(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@PostMapping("/{shelterId}/add/{dogId}")
	@Secured("ROLE_SHELTER")
	public ResponseEntity<HttpStatus> addDogToShelter(@PathVariable("shelterId") long shelterId,
													  @PathVariable("dogId") long dogId,
													  @AuthenticationPrincipal User user) {
		if (!user.getUsername().equals(shelterService.getShelter(shelterId).getUsername())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Shelter shelter=shelterService.getShelter(shelterId);
		Dog dog=dogService.getDog(dogId);
		if(dog.getShelter()!=null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		shelterService.addDog(shelter,dog);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/{shelterId}/{dogId}/delete")
	@Secured("ROLE_SHELTER")
	public ResponseEntity<HttpStatus> deleteDogFromShelter(@PathVariable("shelterId") long shelterId,@PathVariable("dogId") long dogId,
														   @AuthenticationPrincipal User user) {
		if(!user.getUsername().equals(shelterService.getShelter(shelterId).getUsername()))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		shelterService.deleteDog(dogService.getDog(dogId));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{shelterId}/dogs")
	public List<Dog> getDogsByShelterId(@PathVariable("shelterId") long shelterId) {
		//Shelter shelter=shelterService.getShelter(shelterId);
		List<Dog> dogs = dogService.getSheltersDogs(shelterId);
		return dogs;
	}

	@PostMapping("/{shelterId}/dogs/{dogId}/update")
	@Secured("ROLE_SHELTER")
	public ResponseEntity<Dog> updateDog(@PathVariable("shelterId") long shelterId,@PathVariable("dogId") long dogId,
												@RequestBody Dog dog,
												@AuthenticationPrincipal User user) {
		if(!user.getUsername().equals(shelterService.getShelter(shelterId).getUsername()))
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		Dog oldDog = dogService.getDog(dogId);
		dog.setLocation(oldDog.getLocation());
		dog.setShelter(oldDog.getShelter());
		dog.setDogId(oldDog.getDogId());
		dogService.setDog(dog);
		return new ResponseEntity<Dog>(dog, HttpStatus.OK);
	}

	@GetMapping("/info/{id}")
	public ResponseEntity<Shelter> getShelterInfo(@PathVariable("id") long id) {
		return new ResponseEntity<Shelter>(shelterService.getShelter(id), HttpStatus.OK);
	}

	@PostMapping("/update/{id}")
	@Secured("ROLE_SHELTER")
	public ResponseEntity<Shelter> updateShelterInfo(@RequestBody Shelter shelter, @PathVariable("id") long id,
													 @AuthenticationPrincipal User user) {
		if(!user.getUsername().equals(shelterService.getShelter(id).getUsername()))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		Shelter shelter1 = shelterService.getShelter(id);
		if (shelter1 == null)
			return new ResponseEntity<Shelter>(HttpStatus.BAD_REQUEST);
		shelter.setOIB(shelter1.getOIB());
		shelter.setShelterId(id);
		//shelter.setImage(shelter1.getImage());
		if(shelter.getAddress().equals(shelter1.getAddress()) && shelter.getCity().equals(shelter1.getCity()))
			shelter.setLocation(shelter1.getLocation());
		else {
			Location location =locationService.getByAddressAndCity(shelter.getAddress(), shelter.getCity());
			if(location != null)
				shelter.setLocation(location);
			else {
				Location locationNew = new Location();
				locationNew.setAddress(shelter.getAddress());
				locationNew.setCity(shelter.getCity());
				shelter.setLocation(locationNew);
			}
		}
		shelterService.registerShelter(shelter);
		return new ResponseEntity<Shelter>(shelter, HttpStatus.OK);
	}
}

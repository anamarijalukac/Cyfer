package cyfer.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cyfer.domain.Dog;
import cyfer.service.IDogService;

@RestController
@RequestMapping("user")
public class DogController {

	@Autowired
	private IDogService dogService;

	@GetMapping("dog/id/{dogId}")
	public ResponseEntity<Dog> getDog(@PathVariable("dogId") long dogId) {
		Dog dog = dogService.getDog(dogId);
		return new ResponseEntity<Dog>(dog, HttpStatus.OK);
	}

	@GetMapping("dog/shelter/{shelterOIB}")
	public ResponseEntity<Dog> getDogByShelterOIB(@PathVariable("shelterOIB") String shelterOIB) {
		Dog dog = dogService.getDogByShelterOIB(shelterOIB);
		return new ResponseEntity<Dog>(dog, HttpStatus.OK);
	}

	@GetMapping("dog")
	public ResponseEntity<List<Dog>> getAllDogs() {
		List<Dog> list = dogService.getAllDogs();
		return new ResponseEntity<List<Dog>>(list, HttpStatus.OK);
	}

}
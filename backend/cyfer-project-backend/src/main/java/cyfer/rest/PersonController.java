package cyfer.rest;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cyfer.domain.Dog;
import cyfer.domain.Person;
import cyfer.service.IPersonService;

@RestController
@RequestMapping("user")
public class PersonController {
	
	@Autowired
	private IPersonService personService;
	
	@GetMapping("person/{personUsername}")
	public ResponseEntity<Person> getPersonBypersonUsername(@PathVariable("personUsername") String personUsername) {
		Person person = personService.getPersonBypersonUsername(personUsername);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	@GetMapping("person")
	public ResponseEntity<List<Person>> getAllPerson() {
		
		List<Person> list = personService.getAllPerson();
		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
	}
}

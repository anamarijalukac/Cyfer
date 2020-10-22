package cyfer.rest;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cyfer.domain.Shelter;
import cyfer.service.IShelterService;

@RestController
@RequestMapping("user")
public class ShelterController {
	
	@Autowired
	private IShelterService shelterService;
	
	@GetMapping("shelters")
	public ResponseEntity<List<Shelter>> getAllShelter() {
		
		List<Shelter> list = shelterService.getAllShelters();
		return new ResponseEntity<List<Shelter>>(list, HttpStatus.OK);
	}
}

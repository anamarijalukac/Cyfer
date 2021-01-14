package cyfer.rest;

import cyfer.domain.Shelter;
import cyfer.service.IReservationService;
import cyfer.service.IShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IShelterService shelterService;

    @GetMapping("/ranking/1")
    public Map<String, Integer> getRankingByDuration() {
        return reservationService.getRankListByWalkDuration();
    }

    @GetMapping("/ranking/2")
    public Map<String, Integer> getRankingByWalks() {
        return reservationService.getRankListByWalkNumber();
    }

    @GetMapping("/ranking/3")
    public Map<String, Integer> getRankingByDogs() {
        return reservationService.getRankListByDogNumber();
    }

    @GetMapping("")
    public ResponseEntity<List<Shelter>> getAllShelter() {
        List<Shelter> list = shelterService.getAllShelters();
        return new ResponseEntity<List<Shelter>>(list, HttpStatus.OK);
    }
}

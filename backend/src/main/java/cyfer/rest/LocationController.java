package cyfer.rest;

import cyfer.domain.Location;
import cyfer.domain.Shelter;
import cyfer.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private ILocationService locationService;

    @GetMapping("/id/{id}")
    public Location getLocation(@PathVariable("id") long id) {
        return locationService.getLocation(id);
    }
}

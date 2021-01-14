package cyfer.service.impl;

import cyfer.dao.LocationRepository;
import cyfer.domain.Location;
import cyfer.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        List<Location> list = new ArrayList<>();
        locationRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Location getLocation(long id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location getByAddressAndCity(String address, String city) {
        return locationRepository.findByAddressAndCity(address, city);
    }

}

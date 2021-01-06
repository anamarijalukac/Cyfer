package cyfer.service;

import cyfer.domain.Location;

import java.util.List;

public interface ILocationService {
    List<Location> getAllLocations();

    Location getLocation(long id);

    Location saveLocation(Location location);

    Location getByAddressAndCity(String address, String city);
}

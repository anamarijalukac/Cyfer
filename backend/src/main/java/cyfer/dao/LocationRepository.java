package cyfer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cyfer.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    public Location findByAddressAndCity(String address, String city);
}

package cyfer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cyfer.domain.Location;
import cyfer.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}

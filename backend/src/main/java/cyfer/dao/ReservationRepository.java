package cyfer.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cyfer.domain.Dog;
import cyfer.domain.Location;
import cyfer.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@Query("select c.dog from Reservation c ORDER BY c.duration")
	List<Dog> findDogsStatisticsFromReservation(Pageable pageable);
}

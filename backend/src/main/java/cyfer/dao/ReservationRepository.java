package cyfer.dao;

import java.util.List;

import cyfer.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@Query("select c.dog from Reservation c ORDER BY c.duration")
	List<Dog> findDogsStatisticsFromReservation(Pageable pageable);

	List<Reservation> findByWalkAndWalker(Walk walk, Walker walker);
}

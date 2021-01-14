package cyfer.dao;

import java.util.List;

import cyfer.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{



	List<Reservation> findByWalkAndWalker(Walk walk, Walker walker);

    Reservation findByWalkAndDog(Walk walk, Dog dog);

    List<Reservation> findByWalker(Walker walker);

    List<Reservation> findByDog(Dog dog);

}

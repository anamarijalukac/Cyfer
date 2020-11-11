package cyfer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cyfer.domain.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Long>{

	Shelter findByOIB(String OIB);

	Shelter findByUsername(String username);
}

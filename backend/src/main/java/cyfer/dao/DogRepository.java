package cyfer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import cyfer.domain.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {
}

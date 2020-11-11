package cyfer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import cyfer.domain.Walker;

public interface WalkerRepository extends JpaRepository<Walker, Long> {

	Walker findByUsername(String username);

}

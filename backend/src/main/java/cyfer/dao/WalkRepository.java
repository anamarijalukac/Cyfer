package cyfer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cyfer.domain.Walk;

public interface WalkRepository extends JpaRepository<Walk, Long> {

}
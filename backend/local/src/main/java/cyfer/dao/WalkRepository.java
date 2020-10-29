package cyfer.dao;

import org.springframework.data.repository.CrudRepository;

import cyfer.domain.Walk;
import cyfer.domain.WalkId;

public interface WalkRepository extends CrudRepository<Walk, WalkId> {

}

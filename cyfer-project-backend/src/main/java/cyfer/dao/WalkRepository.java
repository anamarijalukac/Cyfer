package cyfer.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cyfer.domain.Walk;
import cyfer.domain.WalkId;

public interface WalkRepository extends CrudRepository<Walk, WalkId> {

	@Query("SELECT a FROM Walk a WHERE a.walkDate_n_time=:walkDate_n_time and a.personUsername=:personUsername and a.dogId=:dogId")
    Walk findByPK(@Param("walkDate_n_time") Timestamp walkDate_n_time, @Param("personUsername") String personUsername,@Param("dogId") String dogId);

}
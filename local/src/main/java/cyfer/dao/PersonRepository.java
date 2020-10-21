package cyfer.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import cyfer.domain.Person;

public interface PersonRepository extends CrudRepository<Person, String> {}

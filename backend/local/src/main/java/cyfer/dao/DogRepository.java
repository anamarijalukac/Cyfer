package cyfer.dao;

import org.springframework.data.repository.CrudRepository;

import cyfer.domain.Dog;

public interface DogRepository extends CrudRepository<Dog, Integer> {}

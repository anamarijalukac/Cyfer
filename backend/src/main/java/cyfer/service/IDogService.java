package cyfer.service;

import java.util.List;

import cyfer.domain.Dog;

public interface IDogService {
	
	List<Dog> getAllDogs();
	Dog getDog(long dogId);
	void setDog(Dog dog);
}

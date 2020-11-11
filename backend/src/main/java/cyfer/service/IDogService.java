package cyfer.service;

import java.util.List;

import cyfer.domain.Dog;

public interface IDogService {
	Dog getDogByShelterOIB(String shelterOIB);
	List<Dog> getAllDogs();
	Dog getDog(long dogId);
}

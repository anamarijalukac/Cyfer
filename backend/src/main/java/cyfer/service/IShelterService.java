package cyfer.service;

import java.util.List;

import cyfer.domain.Dog;
import cyfer.domain.Shelter;
import cyfer.domain.Walker;

public interface IShelterService {
	Shelter registerShelter(Shelter shelter);

	

	Shelter getShelterByOIB(String shelterOIB);

	List<Shelter> getAllShelters();

	Shelter getByUsername(String shelterUsername);

	void deleteShelter(long id);

	void addDog(Shelter shelter,Dog dog);

	void deleteDog(Dog dog);

	Shelter getShelter(long id);
}

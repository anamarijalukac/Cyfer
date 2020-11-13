package cyfer.service;

import java.util.List;

import cyfer.domain.Shelter;

public interface IShelterService {
	Shelter registerShelter(Shelter shelter);

	Shelter getShelter(long id);

	Shelter getShelterByOIB(String shelterOIB);

	List<Shelter> getAllShelters();

	Shelter getByUsername(String shelterUsername);
}

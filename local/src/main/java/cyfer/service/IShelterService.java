package cyfer.service;

import java.util.List; 

import cyfer.domain.Shelter;

public interface IShelterService {
	Shelter getShelterByOIB(String shelterOIB);
	List<Shelter> getAllShelters();
}

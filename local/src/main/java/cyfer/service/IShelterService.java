package cyfer.service;

import cyfer.domain.Shelter;

public interface IShelterService {
	Shelter getShelterByOIB(String shelterOIB);
	Shelter[] getAllShelters();
}

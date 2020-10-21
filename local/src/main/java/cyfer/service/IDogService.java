package cyfer.service;

import cyfer.domain.Dog;

public interface IDogService {
	Dog getDogByShelterOIB(String shelterOIB);
	Dog[] getAllDogs();
}

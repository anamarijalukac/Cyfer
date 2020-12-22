package cyfer.service.impl;

import cyfer.dao.DogRepository;
import cyfer.dao.ShelterRepository;
import cyfer.domain.Dog;
import cyfer.domain.Shelter;
import cyfer.service.IShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelterService implements IShelterService {

    @Autowired
    private ShelterRepository shelterRepository;
    //@Autowired
    //private DogRepository dogRepository;

    @Override
    public Shelter getShelterByOIB(String OIB) {
        return shelterRepository.findByOIB(OIB);
    }

    @Override
    public List<Shelter> getAllShelters() {
        List<Shelter> list = new ArrayList<>();
        shelterRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Shelter registerShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    @Override
    public Shelter getByUsername(String username) {
        return shelterRepository.findByUsername(username);
    }

    @Override
    public void deleteShelter(long id) {
        shelterRepository.deleteById(id);
    }

    @Override
    public void addDog(Shelter shelter, Dog dog) {
        dog.setShelter(shelter);
        //dogRepository.save(dog);

    }

    //rijesiti ovaj bug!!!
    @Override
    public Shelter getShelter(long id) {

        //List<Shelter> list = new ArrayList<>();
        //shelterRepository.findAll().forEach(e -> list.add(e));

        boolean check = shelterRepository.existsById(id);
        System.out.println(check);
        return shelterRepository.findById(id).get();


    }


}

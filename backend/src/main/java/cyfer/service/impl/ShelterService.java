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
    @Autowired
    private DogRepository dogRepository;

    @Override
    public Shelter getShelterByOIB(String OIB) {
        return shelterRepository.findByOIB(OIB);
    }

    @Override
    public List<Shelter> getAllShelters() {
        List<Shelter> list = new ArrayList<>();
        shelterRepository.findAll().forEach(e -> list.add(e));
        long id = list.get(0).getShelterId();
        Shelter s = shelterRepository.findById(id).get();
        System.out.println(s.getShelterId() + s.getName());
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
        dogRepository.save(dog);

    }

    //rijesiti ovaj bug!!!
    @Override
    public Shelter getShelter(Long broj) {

        List<Shelter> list = new ArrayList<>();
        shelterRepository.findAll().forEach(e -> list.add(e));


        Shelter s = shelterRepository.findById(broj).get();
        return s;

    }


}

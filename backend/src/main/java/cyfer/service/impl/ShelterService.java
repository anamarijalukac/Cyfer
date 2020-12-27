package cyfer.service.impl;

import cyfer.dao.DogRepository;
import cyfer.dao.ReservationRepository;
import cyfer.dao.ShelterRepository;
import cyfer.domain.Dog;
import cyfer.domain.Reservation;
import cyfer.domain.Shelter;
import cyfer.service.IShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelterService implements IShelterService {

    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private ReservationRepository reservationRepository;

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
        shelter.setPassword(new BCryptPasswordEncoder().encode(shelter.getPassword()));
        return shelterRepository.save(shelter);
    }

    @Override
    public Shelter getByUsername(String username) {
        return shelterRepository.findByUsername(username);
    }

    @Override
    public void deleteShelter(long id) {
        List<Dog> list = dogRepository.findAll().stream()
                .filter(d -> d.getShelter().getShelterId() == id).collect(Collectors.toList());
        for(Dog d : list)
            deleteDog(d);

        shelterRepository.deleteById(id);
    }

    @Override
    public void addDog(Shelter shelter, Dog dog) {
        dog.setShelter(shelter);
        dogRepository.save(dog);
    }

    @Override
    public void deleteDog(Dog dog) {
        List<Reservation> list = reservationRepository.findAll().stream()
                .filter(r -> r.getDog().getDogId() == dog.getDogId()).collect(Collectors.toList());
        for(Reservation r : list)
            reservationRepository.deleteById(r.getReservationId());

        dogRepository.delete(dog);
    }


    @Override
    public Shelter getShelter(long id) {
        return shelterRepository.findById(id).get();

    }


}

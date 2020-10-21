package cyfer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyfer.dao.PersonRepository;
import cyfer.domain.Person;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public boolean registerPerson(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person getPersonByUsername(String personUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getAllPerson() {
		List<Person> list = new ArrayList<>();
		personRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	

}

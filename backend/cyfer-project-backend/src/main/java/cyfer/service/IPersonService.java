package cyfer.service;

import java.util.List; 

import cyfer.domain.Person;

public interface IPersonService {
	boolean registerPerson(Person person);
	Person getPersonBypersonUsername(String personUsername);
	List<Person> getAllPerson();
}

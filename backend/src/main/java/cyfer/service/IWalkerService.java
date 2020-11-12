package cyfer.service;

import java.util.List;

import cyfer.domain.Walker;

public interface IWalkerService {

	Walker registerWalker(Walker walker);


	Walker getByUsername(String username);

	List<Walker> getAllWalkers();


	Walker getWalker(long id);


	void delete(Walker walker);


	

	

}

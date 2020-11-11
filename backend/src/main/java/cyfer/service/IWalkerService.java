package cyfer.service;

import java.util.List;

import cyfer.domain.Walker;

public interface IWalkerService {

	Walker registerWalker(Walker walker);

	Walker getWalker(long id);

	Walker getByUsername(String username);

	List<Walker> getAllWalkers();

	Walker getWalker(String username);

}

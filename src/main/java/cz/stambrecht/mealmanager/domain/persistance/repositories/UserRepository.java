package cz.stambrecht.mealmanager.domain.persistance.repositories;

import org.springframework.data.repository.CrudRepository;

import cz.stambrecht.mealmanager.domain.persistance.entities.User;

/**
 * User repository interface
 * 
 * @author pavel
 *
 */
public interface UserRepository extends CrudRepository<User,Long> {
	
}

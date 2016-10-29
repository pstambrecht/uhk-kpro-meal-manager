package cz.stambrecht.mealmanager.repositories;

import org.springframework.data.repository.CrudRepository;

import cz.stambrecht.mealmanager.entities.User;

/**
 * User repository interface
 * @author pavel
 *
 */
public interface UserRespository extends CrudRepository<User,Long>{

}

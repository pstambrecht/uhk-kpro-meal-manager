package cz.stambrecht.mealmanager.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import cz.stambrecht.mealmanager.model.User;

/**
 * User repository interface
 * 
 * @author pavel
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
	//empty
}

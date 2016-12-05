package cz.stambrecht.mealmanager.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import cz.stambrecht.mealmanager.model.Meal;


/**
 * User repository interface
 * 
 * @author pavel
 *
 */
public interface MealRepository extends PagingAndSortingRepository<Meal, Long> {

	/**
	 * Finds meal by id. Returns null if not found
	 * @param id meal id
	 */
	Meal findMealById(long id);
}

package cz.stambrecht.mealmanager.domain.persistance.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import cz.stambrecht.mealmanager.domain.persistance.entities.Meal;
import cz.stambrecht.mealmanager.domain.persistance.entities.User;

/**
 * User repository interface
 * 
 * @author pavel
 *
 */
public interface MealRepository extends PagingAndSortingRepository<Meal, Long> {

}

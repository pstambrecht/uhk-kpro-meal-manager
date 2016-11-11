package cz.stambrecht.mealmanager.services.interfaces;

import java.util.List;

import cz.stambrecht.mealmanager.domain.persistance.entities.Meal;
import cz.stambrecht.mealmanager.domain.persistance.entities.User;


public interface MealsService {

	/**
	 * Returns all meals
	 * @return List of users. Never empty array
	 */
	public List<Meal> getMeals();
	
	/**
	 * Create new meal
	 * @param meal
	 */
	public void createMeal(Meal meal);
}

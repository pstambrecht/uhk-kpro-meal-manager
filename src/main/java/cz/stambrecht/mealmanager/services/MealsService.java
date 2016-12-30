package cz.stambrecht.mealmanager.services;

import java.util.List;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Portion;
import cz.stambrecht.mealmanager.model.User;

public interface MealsService {

	/**
	 * Returns all meals
	 * 
	 * @return List of users. Never empty array
	 */
	public List<Meal> getMeals();

	/**
	 * Create new meal
	 * 
	 * @param meal
	 */
	public Meal createMeal(Meal meal);

	/**
	 * Find meal by id.
	 * 
	 * @param mealId
	 * @return Meal or null if no meal found
	 */
	public Meal findMealById(long mealId);

	/**
	 * Adds portion to meal
	 * 
	 * @param mealId
	 *            meal id
	 * @return true if was added
	 */
	public boolean addPortionToMealWithId(long mealId, User diner);
}

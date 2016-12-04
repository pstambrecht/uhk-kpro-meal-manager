package cz.stambrecht.mealmanager.services;

import java.util.List;

import cz.stambrecht.mealmanager.model.Meal;

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
	 * @param mealId 
	 * @return Meal or null if no meal found
	 */
	public Meal findMealById(long mealId);
}

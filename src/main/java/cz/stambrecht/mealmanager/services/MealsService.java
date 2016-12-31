package cz.stambrecht.mealmanager.services;

import java.util.List;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Portion;
import cz.stambrecht.mealmanager.model.User;
import javassist.NotFoundException;

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
	public Meal createMeal(Meal meal) throws NullPointerException;

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
	public void addPortionToMealWithId(long mealId, User diner) throws NullPointerException, NotFoundException;

	/**
	 * Remove portion from meal
	 * 
	 * @param mealId
	 *            meal id
	 */
	public void removePortionFromMealWithId(long mealId, User diner) throws NullPointerException, NotFoundException;

	/**
	 * Close meal and generate transactions
	 * 
	 * @param mealId
	 * @return
	 */
	public void closeMealWithId(long mealId) throws NotFoundException;
}

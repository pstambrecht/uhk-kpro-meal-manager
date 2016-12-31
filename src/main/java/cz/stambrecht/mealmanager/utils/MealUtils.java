package cz.stambrecht.mealmanager.utils;

public class MealUtils {

	/**
	 * Compute price for portion
	 * 
	 * @param totalPrice
	 *            must be greater or equal than 0
	 * @param portionCount
	 *            must be greater than 0
	 * @return price of -1 if error
	 */
	public static float computePriceForPortion(float totalPrice, int portionCount) {
		if (portionCount < 1 || totalPrice < 0) {
			return -1f;
		}

		return totalPrice / portionCount;
	}
}

package cz.stambrecht.mealmanager.model;

public class MealPortionForm {

	private long mealId;
	private User diner;

	/**
	 * @return the mealId
	 */
	public long getMealId() {
		return mealId;
	}

	/**
	 * @param mealId
	 *            the mealId to set
	 */
	public void setMealId(long mealId) {
		this.mealId = mealId;
	}

	/**
	 * @return the diner
	 */
	public User getDiner() {
		return diner;
	}

	/**
	 * @param diner the diner to set
	 */
	public void setDiner(User diner) {
		this.diner = diner;
	}

	

}

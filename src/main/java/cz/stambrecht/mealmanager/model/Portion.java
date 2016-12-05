package cz.stambrecht.mealmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Portion extends AbstractEntityObject {
	private static final int MIN_COUNT_VALUE = 1;

	@Column(name = "portion_count")
	private int count = MIN_COUNT_VALUE;

	@Column(name = "portion_price")
	private float portionPrice;

	@JoinColumn(name = "diner")
	@ManyToOne
	private User diner;

	public Portion() {

	}

	public Portion(User diner) {
		this.diner = diner;
	}

	/**
	 * @return the portionPrice
	 */
	public float getPortionPrice() {
		return portionPrice;
	}

	/**
	 * @param portionPrice
	 *            the portionPrice to set
	 */
	public void setPortionPrice(float portionPrice) {
		this.portionPrice = portionPrice;
	}

	/**
	 * @return the diner
	 */
	public User getDiner() {
		return diner;
	}

	/**
	 * @param diner
	 *            the diner to set
	 */
	public void setDiner(User diner) {
		this.diner = diner;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	
}

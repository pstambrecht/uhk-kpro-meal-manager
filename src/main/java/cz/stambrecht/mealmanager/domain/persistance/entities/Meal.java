package cz.stambrecht.mealmanager.domain.persistance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Meal {
	private static final int MIN_PORTION_COUNT = 1;

	/**
	 * Attributes
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "name")
	@NotNull(message = "Není vyplněn název jídla")
	@Size(min = 1, message = "Není vyplněn název jídla")
	private String name;
	@Column(name = "total_price")
	@Min(value = 0, message = "Celková cena musí být větší nebo rovna 0 Kč")
	private float totalPrice;
	@Column(name = "portion_count")
	@Min(value = MIN_PORTION_COUNT, message = "Minimální počet porcí je " + MIN_PORTION_COUNT)
	private int portionCount = MIN_PORTION_COUNT;
	@Column(name = "portion_price")
	private float portionPrice;
	@JoinColumn(name = "owner")
	@NotNull(message = "Není vybrán tvůrce jídla")
	@ManyToOne
	private User owner = null;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the portionCount
	 */
	public int getPortionCount() {
		return portionCount;
	}

	/**
	 * @param portionCount
	 *            the portionCount to set
	 */
	public void setPortionCount(int portionCount) {
		this.portionCount = portionCount;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	
}

package cz.stambrecht.mealmanager.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cz.stambrecht.mealmanager.utils.MealUtils;

@Entity
public class Meal extends AbstractEntityObject {
	
	public enum State{
		OPENED,
		CLOSED
	}
	/**
	 * Attributes
	 */
	@Column(name = "name")
	@NotNull(message = "Není vyplněn název jídla")
	@Size(min = 1, message = "Není vyplněn název jídla")
	private String name;
	@Column(name = "total_price")
	@Min(value = 0, message = "Celková cena musí být větší nebo rovna 0 Kč")
	private float totalPrice;
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state = State.OPENED;
	@JoinColumn(name = "owner")
	@NotNull(message = "Není vybrán tvůrce jídla")
	@ManyToOne
	private User owner;
	@JoinColumn(name = "portions")
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Portion> portions = new ArrayList<>();

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
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * One portion in list may represents more portions in real (depends on portions count)
	 * @return the portions
	 */
	public List<Portion> getPortions() {
		return portions;
	}

	/**
	 * Returns total portions count
	 * 
	 * @return
	 */
	public int getPortionsCount() {
		int count = 0;
		for (Portion p : portions) {
			count += p.getCount();
		}
		return count;
	}

	/**
	 * 
	 * @return
	 */
	public float getPortionPrice() {
		int portionsCount = getPortionsCount();
		return MealUtils.computePriceForPortion(totalPrice, portionsCount != 0 ? portionsCount : 1);
	}
}

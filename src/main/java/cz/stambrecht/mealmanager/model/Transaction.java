package cz.stambrecht.mealmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction extends AbstractEntityObject {

	/**
	 * Attributes
	 */
	@Column(name = "description")
	private String description;
	@Column(name = "money")
	private float money;
	@JoinColumn(name = "user")
	@NotNull(message = "Neznámý uživatel")
	@ManyToOne
	private User user;

	/**
	 * Empty constructor
	 */
	public Transaction() {

	}

	/**
	 * Constructor
	 * 
	 * @param user
	 * @param money
	 * @param description
	 */
	public Transaction(final User user, final float money, final String description) {
		this.user = user;
		this.money = money;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the money
	 */
	public float getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(float money) {
		this.money = money;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}

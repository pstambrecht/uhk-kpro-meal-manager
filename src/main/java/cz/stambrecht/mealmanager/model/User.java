package cz.stambrecht.mealmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntityObject{

	@Column(name = "name")
	@NotNull
	@Size(min = 3, max = 30, message = "Jméno musí mít minimálně 3 znaky a maximálně 30 znaků")
	private String name;
	@Column(name = "surname")
	@NotNull
	@Size(min = 3, max = 30, message = "Příjmení musí mít minimálně 3 znaky a maximálně 30 znaků")
	private String surname;

	/**
	 * Empty constructor. Do not use it. It is used only for JPA
	 */
	protected User() {
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            String value of user name
	 * @param surname
	 *            String value of user surname
	 */
	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Id getter
	 * 
	 * @return return user id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Name getter
	 * 
	 * @return String name or empty null
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter
	 * 
	 * @param mName
	 *            String value
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Surname getter
	 * 
	 * @return String surname or null
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Surname setter
	 * 
	 * @param mSurname
	 *            String value
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, name='%s', surname='%s']", id, name, surname);
	}
}

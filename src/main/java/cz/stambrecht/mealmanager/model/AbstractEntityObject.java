package cz.stambrecht.mealmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
@Inheritance
public abstract class AbstractEntityObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
}

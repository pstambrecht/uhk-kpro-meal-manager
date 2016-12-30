package cz.stambrecht.mealmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntityObject {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected long id;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
}

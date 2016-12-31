package cz.stambrecht.mealmanager.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class AbstractEntityObject {
	public static final long UNDEFINED_ID = 0L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected long id = UNDEFINED_ID;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Id setter
	 * @param id of user
	 */
	public void setId(long id){
		this.id = id;
	}
}

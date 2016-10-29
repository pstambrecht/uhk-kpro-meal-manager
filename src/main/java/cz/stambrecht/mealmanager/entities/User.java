package cz.stambrecht.mealmanager.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	/**
	 * Attributes
	 */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long mId;
	private String mName;
	private String mSurname;
	
	/**
	 * Empty constructor. Do not use it. It is used only for JPA
	 */
	protected User(){}
	
	/**
	 * Constructor
	 * @param name String value of user name
	 * @param surname String value of user surname
	 */
	public User(String name, String surname){
		mName = name;
		mSurname = surname;
	}
	
	/**
	 * Id getter
	 * @return return user id
	 */
	public long getId() {
		return mId;
	}
	
	/**
	 * Name getter
	 * @return String name or empty null
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * Name setter
	 * @param mName String value
	 */
	public void setName(String mName) {
		this.mName = mName;
	}
	
	/**
	 * Surname getter
	 * @return String surname or null
	 */
	public String getSurname() {
		return mSurname;
	}
	
	/**
	 * Surname setter
	 * @param mSurname String value
	 */
	public void setSurname(String mSurname) {
		this.mSurname = mSurname;
	}
	
    @Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s', surname='%s']",
                mId, mName, mSurname);
    }
}

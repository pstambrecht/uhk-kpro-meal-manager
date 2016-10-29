package cz.stambrecht.mealmanager.model;

public class User {
	/**
	 * Attributes
	 */
	private long mId;
	private String mName;
	private String mSurname;
	
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
}

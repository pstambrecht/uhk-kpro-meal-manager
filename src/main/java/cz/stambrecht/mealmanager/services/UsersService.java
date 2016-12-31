package cz.stambrecht.mealmanager.services;

import java.util.List;

import cz.stambrecht.mealmanager.model.User;

public interface UsersService {

	/**
	 * Users getter.
	 * 
	 * @return List of users. Never empty array
	 */
	public List<User> getUsers();

	/**
	 * User getter by id
	 * 
	 * @param id
	 *            user id
	 * @return User object or @{code null}
	 */
	public User getUser(long id);

	/**
	 * Create new user
	 * 
	 * @param user
	 * @throws NullPointerException
	 *             if user is @{code null}
	 */
	public void createUser(User user) throws NullPointerException;

	/**
	 * Update existing user
	 * 
	 * @param user
	 * @throws NullPointerException
	 */
	public void updateUser(User user) throws NullPointerException, IllegalStateException;
}

package cz.stambrecht.mealmanager.services;

import java.util.List;

import cz.stambrecht.mealmanager.model.User;

public interface UsersService {

	/**
	 * Returns all users
	 * 
	 * @return List of users. Never empty array
	 */
	public List<User> getUsers();

	public void createUser(User user);
}

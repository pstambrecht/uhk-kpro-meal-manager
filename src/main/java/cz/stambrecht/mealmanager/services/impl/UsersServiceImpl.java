package cz.stambrecht.mealmanager.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cz.stambrecht.mealmanager.domain.persistance.entities.User;
import cz.stambrecht.mealmanager.domain.persistance.repositories.UserRepository;
import cz.stambrecht.mealmanager.services.interfaces.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository mUserRepository;

	@Override
	public List<User> getUsers() {
		return Lists.newArrayList(mUserRepository.findAll());
	}

	@Override
	public void createUser(User user) {
		mUserRepository.save(user);

	}

}

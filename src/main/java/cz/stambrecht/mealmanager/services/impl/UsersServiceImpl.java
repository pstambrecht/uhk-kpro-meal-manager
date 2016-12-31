package cz.stambrecht.mealmanager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import cz.stambrecht.mealmanager.model.User;
import cz.stambrecht.mealmanager.repositories.UserRepository;
import cz.stambrecht.mealmanager.services.TransactionsService;
import cz.stambrecht.mealmanager.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		return Lists.newArrayList(userRepository.findAll(new Sort(Direction.DESC,"id")));
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);

	}

}

package cz.stambrecht.mealmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.stambrecht.mealmanager.entities.User;
import cz.stambrecht.mealmanager.repositories.UserRespository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRespository mUserRepository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Iterable<User> getUsers() {
		return mUserRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody void addUser() {
		mUserRepository.save(new User("Karel", "Java"));
	}
}

package cz.stambrecht.mealmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.stambrecht.mealmanager.domain.persistance.entities.User;
import cz.stambrecht.mealmanager.services.interfaces.UsersService;

@Controller
public class UsersController {

	@Autowired
	private UsersService mUserService;

	/**
	 * Returns user page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public String getUsersPage(Model model) {
		model.addAttribute("users", mUserService.getUsers());
		return "pages/users";
	}

	@RequestMapping(path = "/users/create", method = RequestMethod.GET)
	public String getCreateUserPage(Model model) {
        model.addAttribute("user", new User("",""));
		return "pages/users_create";
	}
	
	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	public String createTestUser(@ModelAttribute User user) {
		mUserService.createUser(user);
		return "redirect:/users";
	}
	
	

}

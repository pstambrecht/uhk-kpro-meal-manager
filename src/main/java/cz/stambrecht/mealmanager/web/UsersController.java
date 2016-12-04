package cz.stambrecht.mealmanager.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.stambrecht.mealmanager.model.User;
import cz.stambrecht.mealmanager.services.UsersService;

@Controller
public class UsersController {

	@Autowired
	private UsersService userService;

	/**
	 * Returns user page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public String getUsersPage(Model model) {
		model.addAttribute("users", userService.getUsers());
		return "pages/users";
	}

	/**
	 * Returns user create page
	 * @param user 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/users/create", method = RequestMethod.GET)
	public String getCreateUserPage(User user, Model model) {
        model.addAttribute("user", user);
		return "pages/users_create";
	}
	
	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	public String createTestUser(@Valid User user, BindingResult bindingResult) {
		if(bindingResult.hasFieldErrors()){
			return "pages/users_create";
		}
		userService.createUser(user);
		return "redirect:/users";
	}


}

package cz.stambrecht.mealmanager.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.stambrecht.mealmanager.model.User;
import cz.stambrecht.mealmanager.services.TransactionsService;
import cz.stambrecht.mealmanager.services.UsersService;
import cz.stambrecht.mealmanager.web.errors.ResourceNotFoundException;

@Controller
public class UsersController {

	/**
	 * Attributes
	 */
	@Autowired
	private UsersService userService;
	@Autowired
	private TransactionsService transactionsService;

	/**
	 * Users list page getter
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public String getUsersPage(Model model) {
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("usersMoneySum", transactionsService.getTransactionsSumSortedByUserId());
		return "pages/users";
	}

	/**
	 * Create user page getter
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/users/create", method = RequestMethod.GET)
	public String getCreateUserPage(User user, Model model) {
		model.addAttribute("user", user);
		return "pages/users_create";
	}

	/**
	 * Edit user page getter.
	 * 
	 * @param id
	 * @param model
	 * @return Edit page or 404 page if user not found
	 */
	@RequestMapping(path = "/users/{id}/edit", method = RequestMethod.GET)
	public String getUpdateUserPage(@PathVariable long id, Model model) {
		User user;
		try {
			user = userService.getUser(id);
		} catch (NullPointerException e) {
			System.out.println("No user to edit found.");
			throw new ResourceNotFoundException();
		}

		model.addAttribute("user", user);
		return "pages/users_edit";
	}

	/**
	 * Create user endpoint
	 * 
	 * @param user
	 * @param bindingResult
	 * @return users page or create user page with errors
	 */
	@RequestMapping(value = "/users/create", method = RequestMethod.POST)
	public String createUser(@Valid User user, BindingResult bindingResult) {
		// check user object
		if (user == null) {
			throw new ResourceNotFoundException();
		}

		if (bindingResult.hasFieldErrors()) {
			return "pages/users_create";
		}
		try {
			userService.createUser(user);
		} catch (NullPointerException e) {
			System.out.println("Unable to create user.");
			bindingResult.addError(new ObjectError("global", "Ups! Vyskytla se chyba. Zkuste to později."));
			return "pages/users_create";
		}
		return "redirect:/users";
	}

	/**
	 * Update user endpoint
	 * 
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/users/{id}/edit", method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult bindingResult) {

		// check user object
		if (user == null) {
			throw new ResourceNotFoundException();
		}

		if (bindingResult.hasFieldErrors()) {
			return "pages/users_edit";
		}

		try {
			userService.updateUser(user);
		} catch (NullPointerException | IllegalStateException e) {
			System.out.println("Unable to update user.");
			bindingResult.addError(new ObjectError("global", "Ups! Vyskytla se chyba. Zkuste to později."));
			return "pages/users_edit";
		}

		return "redirect:/users";
	}
}

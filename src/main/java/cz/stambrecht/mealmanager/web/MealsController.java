package cz.stambrecht.mealmanager.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.User;
import cz.stambrecht.mealmanager.services.MealsService;
import cz.stambrecht.mealmanager.services.UsersService;
import cz.stambrecht.mealmanager.web.errors.ResourceNotFoundException;

@Controller
public class MealsController {

	@Autowired
	private MealsService mealsService;
	@Autowired
	private UsersService usersService;

	/**
	 * User list model attribute
	 * 
	 * @return List of Users or null
	 */
	@ModelAttribute("users")
	public List<User> getUsers() {
		return usersService.getUsers();
	}

	/**
	 * Returns user page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/meals", method = RequestMethod.GET)
	public String getMealsPage(Model model) {
		model.addAttribute("meals", mealsService.getMeals());
		return "pages/meals";
	}

	/**
	 * Returns create meal page
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/meals/create", method = RequestMethod.GET)
	public String getCreateMealPage(Meal meal, Model model) {
		model.addAttribute("meal", meal);
		return "pages/meals_create";
	}

	@RequestMapping(path = "/meals/{id}", method = RequestMethod.GET)
	public String getMealPage(@PathVariable long id, Model model) {
		Meal meal = mealsService.findMealById(id);
		if (meal == null) {
			throw new ResourceNotFoundException();
		}
		model.addAttribute("meal", meal);
		return "pages/meals_detail";
	}

	/**
	 * Handle form post to create new meal. Validates inputs.
	 * 
	 * @param meal
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/meals/create", method = RequestMethod.POST)
	public String createMeal(@Valid Meal meal, BindingResult bindingResult) {

		if (bindingResult.hasFieldErrors()) {
			return "pages/meals_create";
		}

		meal = mealsService.createMeal(meal);

		return "redirect:/meals/" + meal.getId();
	}

}

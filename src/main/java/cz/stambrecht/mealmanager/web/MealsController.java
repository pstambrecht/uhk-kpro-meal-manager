package cz.stambrecht.mealmanager.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.stambrecht.mealmanager.domain.persistance.entities.Meal;
import cz.stambrecht.mealmanager.domain.persistance.entities.User;
import cz.stambrecht.mealmanager.services.interfaces.MealsService;
import cz.stambrecht.mealmanager.services.interfaces.UsersService;

@Controller
public class MealsController {

	@Autowired
	private MealsService mealsService;
	@Autowired
	private UsersService usersService;

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
	 * Returns
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/meals/create", method = RequestMethod.GET)
	public String getCreateMealPage(Meal meal, Model model) {
		model.addAttribute("meal", meal);
		model.addAttribute("users", usersService.getUsers());
		return "pages/meals_create";
	}

	@RequestMapping(value = "/meals/create", method = RequestMethod.POST)
	public String createTestUser(@Valid Meal meal, Model model, BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors()) {
			return "pages/meals_create";
		}
		mealsService.createMeal(meal);
		return "redirect:/meals";
	}
}

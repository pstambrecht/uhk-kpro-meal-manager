package cz.stambrecht.mealmanager.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.MealPortionForm;
import cz.stambrecht.mealmanager.model.User;
import cz.stambrecht.mealmanager.services.MealsService;
import cz.stambrecht.mealmanager.services.TransactionsService;
import cz.stambrecht.mealmanager.services.UsersService;
import cz.stambrecht.mealmanager.web.errors.ResourceNotFoundException;
import javassist.NotFoundException;

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
	 * Meals list page getter
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
	 * Meal detail page getter
	 * 
	 * @param id
	 * @param mealPortionForm
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/meals/{id}", method = RequestMethod.GET)
	public String getMealPage(@PathVariable long id, MealPortionForm mealPortionForm, Model model) {
		Meal meal = mealsService.findMealById(id);
		if (meal == null) {
			throw new ResourceNotFoundException();
		}
		mealPortionForm.setMealId(meal.getId());
		model.addAttribute("meal", meal);
		model.addAttribute("mealPortionForm", mealPortionForm);

		return "pages/meals_detail";
	}

	/**
	 * Create new meal getter
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

		try {
			meal = mealsService.createMeal(meal);
		} catch (NullPointerException e) {
			System.out.println(e.toString());
			bindingResult.addError(new ObjectError("global", "Ups! Vyskytla se chyba."));
			return "pages/meals_create";
		}
		return "redirect:/meals/" + meal.getId();
	}

	/**
	 * Handle form post to add new meal portion.
	 * 
	 * @param meal
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/meals/portion/create", method = RequestMethod.POST)
	public String createPortion(MealPortionForm mealPortionForm) {
		try {
			mealsService.addPortionToMealWithId(mealPortionForm.getMealId(), mealPortionForm.getDiner());
		} catch (NullPointerException | NotFoundException e) {
			throw new ResourceNotFoundException();
		}

		return "redirect:/meals/" + mealPortionForm.getMealId();
	}

	/**
	 * Handle form post to remove meal portion.
	 * 
	 * @param meal
	 * @return
	 */
	@RequestMapping(value = "/meals/portion/remove", method = RequestMethod.POST)
	public String removePortion(MealPortionForm mealPortionForm) {
		System.out.println("" + mealPortionForm.getMealId());
		try {
			mealsService.removePortionFromMealWithId(mealPortionForm.getMealId(), mealPortionForm.getDiner());
		} catch (NullPointerException | NotFoundException e) {
			// TODO Create better reaction in future
			throw new ResourceNotFoundException();
		}
		return "redirect:/meals/" + mealPortionForm.getMealId();
	}

	/**
	 * Handle form post to remove meal portion.
	 * 
	 * @param meal
	 * @return
	 */
	@RequestMapping(value = "/meals/{id}/close", method = RequestMethod.POST)
	public String closeMeal(@PathVariable long id) {
		try {
			mealsService.closeMealWithId(id);
		} catch (NotFoundException e) {
			// TODO Create better reaction in future
			throw new ResourceNotFoundException();
		}
		return "redirect:/meals/" + id;
	}
}

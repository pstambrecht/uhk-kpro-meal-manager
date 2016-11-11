package cz.stambrecht.mealmanager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cz.stambrecht.mealmanager.domain.persistance.entities.Meal;
import cz.stambrecht.mealmanager.domain.persistance.entities.User;
import cz.stambrecht.mealmanager.domain.persistance.repositories.MealRepository;
import cz.stambrecht.mealmanager.domain.persistance.repositories.UserRepository;
import cz.stambrecht.mealmanager.services.interfaces.MealsService;
import cz.stambrecht.mealmanager.services.interfaces.UsersService;
import cz.stambrecht.mealmanager.utils.MealUtils;

@Service
public class MealsServiceImpl implements MealsService {

	@Autowired
	private MealRepository mealRepository;

	@Override
	public List<Meal> getMeals() {
		return Lists.newArrayList(mealRepository.findAll(new Sort(Direction.DESC, "id")));
	}

	@Override
	public void createMeal(Meal meal) {
		meal.setPortionPrice(MealUtils.computePriceForPortion(meal.getTotalPrice(), meal.getPortionCount()));
		mealRepository.save(meal);
	}
}

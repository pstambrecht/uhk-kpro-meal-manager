package cz.stambrecht.mealmanager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.repositories.MealRepository;
import cz.stambrecht.mealmanager.services.MealsService;
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
	public Meal createMeal(Meal meal) {
		meal.setPortionPrice(MealUtils.computePriceForPortion(meal.getTotalPrice(), meal.getPortionCount()));
		return mealRepository.save(meal);
	}

	@Override
	public Meal findMealById(long mealId) {
		return mealRepository.findMealById(mealId);
	}
}

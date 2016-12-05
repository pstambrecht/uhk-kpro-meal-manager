package cz.stambrecht.mealmanager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Portion;
import cz.stambrecht.mealmanager.model.User;
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
		return mealRepository.save(meal);
	}

	@Override
	public Meal findMealById(long mealId) {
		return mealRepository.findMealById(mealId);
	}

	@Override
	public boolean addPortionToMealWithId(long mealId, User diner) {
		Meal meal = findMealById(mealId);
		if(meal == null){
			return false;
		}
		
		//find portion
		Portion portion = null;
		for(Portion p: meal.getPortions()){
			if(p.getDiner().equals(diner)){
				p.setCount(p.getCount()+1);
				portion = p;
				break;
			}
		}
		
		if(portion == null){		
			meal.getPortions().add(new Portion(diner));
		}
		mealRepository.save(meal);
		return true;
	}
	
	
}

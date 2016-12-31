package cz.stambrecht.mealmanager.services.impl;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Meal.State;
import cz.stambrecht.mealmanager.model.Portion;
import cz.stambrecht.mealmanager.model.User;
import cz.stambrecht.mealmanager.repositories.MealRepository;
import cz.stambrecht.mealmanager.services.MealsService;
import cz.stambrecht.mealmanager.services.TransactionsService;
import javassist.NotFoundException;

@Service
public class MealsServiceImpl implements MealsService {

	@Autowired
	private MealRepository mealRepository;

	@Autowired
	private TransactionsService transactionsService;

	@Override
	public List<Meal> getMeals() {
		return Lists.newArrayList(mealRepository.findAll(new Sort(Direction.DESC, "id")));
	}

	@Override
	public Meal createMeal(Meal meal) throws NullPointerException {

		if (meal == null) {
			throw new NullPointerException("Meal is null");
		}

		return mealRepository.save(meal);
	}

	@Override
	public Meal findMealById(long mealId) {
		return mealRepository.findMealById(mealId);
	}

	@Override
	public void addPortionToMealWithId(long mealId, User diner) throws NullPointerException, NotFoundException {
		if (diner == null) {
			throw new NotFoundException("Diner is null");
		}

		Meal meal = findMealById(mealId);

		if (meal == null) {
			throw new NotFoundException("Meal not found");
		}

		// find portion
		Portion portion = null;
		for (Portion p : meal.getPortions()) {
			if (p.getDiner().equals(diner)) {
				p.setCount(p.getCount() + 1);
				portion = p;
				break;
			}
		}

		if (portion == null) {
			meal.getPortions().add(new Portion(diner));
		}
		mealRepository.save(meal);
	}

	@Override
	public void removePortionFromMealWithId(long mealId, User diner) throws NullPointerException, NotFoundException {
		if (diner == null) {
			throw new NotFoundException("Diner is null");
		}

		Meal meal = findMealById(mealId);

		if (meal == null) {
			throw new NotFoundException("Meal not found");
		}

		// find portion and decrease count or remove it from meal
		Iterator<Portion> portionIterator = meal.getPortions().iterator();
		Portion portion = null;
		while (portionIterator.hasNext()) {
			portion = portionIterator.next();
			if (portion.getDiner().equals(diner)) {
				if (portion.getCount() > 1) {
					portion.setCount(portion.getCount() - 1);
				} else {
					portionIterator.remove();
				}
				mealRepository.save(meal);
			}
		}
	}

	@Override
	@Transactional
	public void closeMealWithId(long mealId) throws NotFoundException {
		Meal meal = findMealById(mealId);
		if (meal == null) {
			throw new NotFoundException("Meal not found");
		}
		meal.setState(Meal.State.CLOSED);
		transactionsService.createTransactionsFromMeal(meal);
		mealRepository.save(meal);
	}

}

package cz.stambrecht.mealmanager.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Portion;
import cz.stambrecht.mealmanager.model.Transaction;
import cz.stambrecht.mealmanager.repositories.TransactionRepository;
import cz.stambrecht.mealmanager.repositories.UserRepository;
import cz.stambrecht.mealmanager.services.TransactionsService;

@Service
public class TransactionsServiceImpl implements TransactionsService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	@Transactional
	public boolean createTransactionsFromMeal(Meal meal) {
		if (meal == null) {
			return false;
		}

		// save chef money
		transactionRepository.save(new Transaction(meal.getOwner(), meal.getTotalPrice(),
				meal.getOwner().getName() + " " + meal.getOwner().getName() + " uvařil jídlo " + meal.getName()));

		float portionPrice = meal.getPortionPrice();
		for (Portion portion : meal.getPortions()) {
			transactionRepository.save(new Transaction(portion.getDiner(), -portion.getCount() * portionPrice,
					portion.getDiner().getName() + " " + portion.getDiner().getName() + " jedl " + portion.getCount()
							+ " jídla " + meal.getName()));
		}

		return true;
	}

	@Override
	public List<Transaction> getTransactions() {
		return Lists.newArrayList(transactionRepository.findAll(new Sort(Direction.DESC, "id")));

	}

}

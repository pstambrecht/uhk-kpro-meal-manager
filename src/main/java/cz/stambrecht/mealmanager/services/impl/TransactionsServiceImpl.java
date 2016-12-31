package cz.stambrecht.mealmanager.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * Attributes
	 */
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> getTransactions() {
		return Lists.newArrayList(transactionRepository.findAll(new Sort(Direction.DESC, "id")));
	}

	@Override
	@Transactional
	public void createTransactionsFromMeal(Meal meal) throws NullPointerException {
		if (meal == null) {
			throw new NullPointerException();
		}

		// save chef money
		transactionRepository.save(new Transaction(meal.getOwner(), meal.getTotalPrice(),
				meal.getOwner().getName() + " " + meal.getOwner().getSurname() + " uvařil(a) jídlo " + meal.getName()));

		// save diner money
		float portionPrice = meal.getPortionPrice();
		for (Portion portion : meal.getPortions()) {
			transactionRepository.save(new Transaction(portion.getDiner(), -portion.getCount() * portionPrice,
					portion.getDiner().getName() + " " + portion.getDiner().getSurname() + " jedl(a) "
							+ portion.getCount() + " ks jídla " + meal.getName()));
		}
	}

	@Override
	public Map<Long, Float> getTransactionsSumSortedByUserId() {
		List<Transaction> transactions = getTransactions();
		Map<Long, Float> sortedTransSum = new HashMap<>();
		Float sum;
		for (Transaction transaction : transactions) {
			sum = sortedTransSum.get(transaction.getUser().getId());
			if (sum == null) {
				sum = new Float(transaction.getMoney());
			} else {
				sum += transaction.getMoney();
			}
			sortedTransSum.put(transaction.getUser().getId(), sum);
		}
		return sortedTransSum;
	}

}

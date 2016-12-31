package cz.stambrecht.mealmanager.services;

import java.util.List;
import java.util.Map;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Transaction;

public interface TransactionsService {

	/**
	 * Transactions getter. 
	 * @return List of transactions. Never null;
	 */
	public List<Transaction> getTransactions();
	
	/**
	 * 
	 * @return Returns map of transactions sums mapped by user. Never null;
	 */
	public Map<Long,Float> getTransactionsSumSortedByUserId();
	
	/**
	 * Create transactions from meal. 
	 * @param meal
	 * @return
	 */
	public void createTransactionsFromMeal(Meal meal) throws NullPointerException;
}

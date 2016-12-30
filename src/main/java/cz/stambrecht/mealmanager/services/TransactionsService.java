package cz.stambrecht.mealmanager.services;

import java.util.List;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Transaction;

public interface TransactionsService {

	public boolean createTransactionsFromMeal(Meal meal);
	
	public List<Transaction> getTransactions();
}

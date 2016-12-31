package cz.stambrecht.mealmanager.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
	/**
	 * Finds transactions by id. Returns null if not found
	 * @param id meal id
	 */
	Meal findMealById(long id);
}

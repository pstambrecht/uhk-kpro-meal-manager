package cz.stambrecht.mealmanager;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cz.stambrecht.mealmanager.model.Meal;
import cz.stambrecht.mealmanager.model.Portion;
import cz.stambrecht.mealmanager.model.Transaction;
import cz.stambrecht.mealmanager.model.User;
import cz.stambrecht.mealmanager.repositories.TransactionRepository;
import cz.stambrecht.mealmanager.repositories.UserRepository;
import cz.stambrecht.mealmanager.services.TransactionsService;
import cz.stambrecht.mealmanager.utils.MealUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealManagerApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TransactionsService transactionsService;
	
	@Autowired
	TransactionRepository transactionsRepository;

	/**
	 * Test portion price computing
	 */
	@Test
	public void test_mealPortionPriceComputing() {
		final int portionCount = 8;
		final int totalPrice = 432;
		float priceForPortion = MealUtils.computePriceForPortion(totalPrice, portionCount);
		assertEquals(54f, priceForPortion, 0.05f);
	}

	@Test
	public void test_userStoring() {
		final User testUser = new User("Tester-ezášěrýá", "User2455");
		long id = userRepository.save(testUser).getId();
		final User loadedUser = userRepository.findOne(id);

		assertEquals("Tester-ezášěrýá", loadedUser.getName());
		assertEquals("User2455", loadedUser.getSurname());
		assertEquals(id, loadedUser.getId());
	}

	@Test
	public void test_creatingTransactionsFromMeal() {
		final Meal meal = new Meal();
		final User owner = new User("Tester", "Testover");
		final User diner = new User("Tester2", "Testover2");
		final Portion portion1 = new Portion(diner);
		final Portion portion2 = new Portion(owner);
		portion1.setCount(2);

		final long ownerId = userRepository.save(owner).getId();
		final long dinerId = userRepository.save(diner).getId();
		
		meal.setName("Guláš");
		meal.setOwner(owner);
		meal.setTotalPrice(300);
		meal.getPortions().add(portion1);
		meal.getPortions().add(portion2);

		transactionsRepository.deleteAll();
		transactionsService.createTransactionsFromMeal(meal);
		List<Transaction> transactions = transactionsService.getTransactions();
		
		assertEquals(transactions.size(),3);
		
		for(Transaction t: transactions){
			if(t.getUser().getId() == ownerId){
				assert(t.getMoney() == 300 || t.getMoney() == -100);
				continue;
			}
			
			if(t.getUser().getId() == dinerId){
				assertEquals(t.getMoney(),-200,0f);
				continue;
			}
			
			assert(false);
		}
	}
}

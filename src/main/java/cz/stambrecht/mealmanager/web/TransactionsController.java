package cz.stambrecht.mealmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.stambrecht.mealmanager.repositories.TransactionRepository;
import cz.stambrecht.mealmanager.services.TransactionsService;

@Controller
public class TransactionsController {

	@Autowired
	private TransactionsService transactionsService;
	
	/**
	 * Returns user page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/transactions", method = RequestMethod.GET)
	public String getTransactionsPage(Model model) {
		model.addAttribute("transactions", transactionsService.getTransactions());
		return "pages/transactions";
	}
}

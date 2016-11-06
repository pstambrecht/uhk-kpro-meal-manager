package cz.stambrecht.mealmanager.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

	/**
	 * Returns dash board
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = { "", "/", "/dashboard" }, method = RequestMethod.GET)
	public String getUsers(Model model) {
		return "pages/dashboard";
	}
}

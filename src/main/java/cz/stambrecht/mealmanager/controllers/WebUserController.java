package cz.stambrecht.mealmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cz.stambrecht.mealmanager.entities.User;
import cz.stambrecht.mealmanager.repositories.UserRespository;

@Controller
@RequestMapping("/users")
public class WebUserController {


	@RequestMapping(method = RequestMethod.GET)
	public String getUsers() {
		return "users";
	}
}

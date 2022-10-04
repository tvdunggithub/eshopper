package com.dungtran.eshopper.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.UserService;

@Controller
public class RegistrationController {

	@Autowired
	UserService us;

	@GetMapping("/register")
	public String Registration(@ModelAttribute("user") User user) {
		return "register";
	}

	@PostMapping("/register")
	public String Registration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
			Model model){
		if (bindingResult.hasErrors()) {
			return "register";
		}
		try {
			us.createUser(user);
		} catch (Exception e) {
			model.addAttribute("usernameError", "Username already exists");
			return "/register";
		}
		return "redirect:/login";
	}

}

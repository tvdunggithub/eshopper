package com.dungtran.eshopper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;

@Controller
public class ErrorController {
	
	@Autowired
	CartItemService cs;
	
	@GetMapping("/error")
	public String error(Model model,@AuthenticationPrincipal User user) {
		if (user != null) {
			int countCart = cs.countByOrderAndUser(null, user);
			model.addAttribute("cartSize", countCart);
		}
		return "error";
	}

}

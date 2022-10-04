package com.dungtran.eshopper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.OrderService;

@Controller
public class UserDetailController {
	
	@Autowired
	OrderService os;
	
	@GetMapping("/user")
	public String error(Model model,@AuthenticationPrincipal User user) {
		List<Order> orders = os.findByUser(user);
		model.addAttribute("orders", orders);		
		return "userDetail";
	}

}

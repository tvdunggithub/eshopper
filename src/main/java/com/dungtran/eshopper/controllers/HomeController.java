package com.dungtran.eshopper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;
import com.dungtran.eshopper.services.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartItemService cs;
	
	@GetMapping(value={"/home","","/"})
	public String home(Model model,@AuthenticationPrincipal User user) {	
		
		int countMen = productService.countByGenderCategory("men");
		int countWomen = productService.countByGenderCategory("women");
		int countBaby = productService.countByGenderCategory("baby");
		if (user != null) {
			int countCart = cs.countByOrderAndUser(null, user);
			model.addAttribute("cartSize", countCart);
		}		
		model.addAttribute("men",countMen);
		model.addAttribute("women",countWomen);
		model.addAttribute("baby",countBaby);
		
		return "home";
	}
	

}

package com.dungtran.eshopper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dungtran.eshopper.config.CustomOAuth2User;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;
import com.dungtran.eshopper.services.ProductService;
import com.dungtran.eshopper.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartItemService cs;
	
	@Autowired
	UserService us;
	
	@GetMapping(value={"/home","","/"})
	public String home(Model model,@AuthenticationPrincipal User user,
			@AuthenticationPrincipal CustomOAuth2User oath2) {	
		User loginUser = us.getLoginUser(user, oath2);
		int countMen = productService.countByGenderCategory("men");
		int countWomen = productService.countByGenderCategory("women");
		int countBaby = productService.countByGenderCategory("baby");
		if (loginUser != null) {
			int countCart = cs.countByOrderAndUser(null, loginUser);
			model.addAttribute("cartSize", countCart);
		}		
		model.addAttribute("men",countMen);
		model.addAttribute("women",countWomen);
		model.addAttribute("baby",countBaby);
		
		return "home";
	}
	

}

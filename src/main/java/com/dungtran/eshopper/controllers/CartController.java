package com.dungtran.eshopper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dungtran.eshopper.entities.CartItem;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;
import com.dungtran.eshopper.services.ProductService;

@Controller
public class CartController {

	@Autowired
	CartItemService cs;

	@GetMapping("/cart")
	public String cart(Model model, @AuthenticationPrincipal User user) {
		List<CartItem> cartItems = cs.findByOrderAndUser(null, user);
		int subTotal = cs.countSubtotal(null,user);
		int total = subTotal + cartItems.size() * 2;
		model.addAttribute("cartSize", cartItems.size());
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("subTotal", subTotal);
		model.addAttribute("total", total);
		return "cart";
	}
	
	

}

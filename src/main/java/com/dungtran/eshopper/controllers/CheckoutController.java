package com.dungtran.eshopper.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dungtran.eshopper.entities.CartItem;
import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;
import com.dungtran.eshopper.services.OrderService;

@Controller
public class CheckoutController {
	
	@Autowired
	CartItemService cs;
	
	@Autowired
	OrderService os;
	
	@PostMapping("/checkout")
	public String checkout(@ModelAttribute("order")Order order,
			Model model,@AuthenticationPrincipal User user) {
		List<CartItem> cartItems = cs.findByOrderAndUser(null, user);
		int subTotal = cs.countSubtotal(null,user);
		int total = subTotal + cartItems.size() * 2;
		model.addAttribute("cartSize", cartItems.size());
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("subTotal", subTotal);
		model.addAttribute("total", total);
		return "checkout";
	}
	
	@PostMapping("/placeOrder")
	public String placeOrder(Model model,@AuthenticationPrincipal User user,
			@ModelAttribute("order") @Valid Order order,BindingResult bindingResult) {
		List<CartItem> cartItems = cs.findByOrderAndUser(null, user);
		if (bindingResult.hasErrors()) {
			int subTotal = cs.countSubtotal(null,user);
			int total = subTotal + cartItems.size() * 2;
			model.addAttribute("cartSize", cartItems.size());
			model.addAttribute("cartItems", cartItems);
			model.addAttribute("subTotal", subTotal);
			model.addAttribute("total", total);
			return "checkout";
		}
		order.setDate(new Date());
		order.setUser(user);
		os.save(order);
		for(CartItem c:cartItems) {
			c.setOrder(order);
			cs.save(c);
		}
		return "thank";
	}

}

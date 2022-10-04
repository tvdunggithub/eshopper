package com.dungtran.eshopper.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.dungtran.eshopper.entities.CartItem;
import com.dungtran.eshopper.entities.Product;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;
import com.dungtran.eshopper.services.ProductService;

@Controller
public class DetailController {

	@Autowired
	ProductService ps;
	
	@Autowired
	CartItemService cs;

	@GetMapping("/detail/{productId}")
	public String getProductDetail(Model model,@AuthenticationPrincipal User user, 
			@PathVariable(value = "productId", required = true) int id) {
		Optional<Product> product = ps.findById(id);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
		}
		if (user != null) {
			int countCart = cs.countByOrderAndUser(null, user);
			model.addAttribute("cartSize", countCart);
		}

		return "detail";
	}


}

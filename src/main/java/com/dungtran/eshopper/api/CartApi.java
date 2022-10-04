package com.dungtran.eshopper.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungtran.eshopper.entities.Order;
import com.dungtran.eshopper.entities.Product;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;
import com.dungtran.eshopper.services.ProductService;

@RestController
@RequestMapping("/api")
public class CartApi {

	@Autowired
	ProductService ps;

	@Autowired
	CartItemService cs;

	@PostMapping("/add/{pid}/{quantity}")
	public String addToCart(HttpServletResponse response, @PathVariable(value = "pid", required = true) String pid,
			@PathVariable(value = "quantity", required = true) String quantity, @AuthenticationPrincipal User user) {
		int subTotal = 0;
		if (user == null) {
			return "You must login";
		} else {
			if (pid != null && quantity != null) {
				cs.addToCart(Integer.parseInt(pid), Integer.parseInt(quantity), user);
			}
			return "Add to cart successfully!";
		}
	}

	@PostMapping("/delete/{pid}")
	public String deleteCart(HttpServletResponse response, @PathVariable(value = "pid", required = true) String pid,
			@AuthenticationPrincipal User user) {
		Product product = ps.findById(Integer.valueOf(pid)).get();
		cs.deleteByOrderAndProduct(null, product, user);
		return "Delete successfully!";
	}

}

package com.dungtran.eshopper.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dungtran.eshopper.config.CustomOAuth2User;
import com.dungtran.eshopper.entities.Product;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;
import com.dungtran.eshopper.services.ProductService;
import com.dungtran.eshopper.services.UserService;

@RestController
@RequestMapping("/api")
public class CartApi {

	@Autowired
	ProductService ps;

	@Autowired
	CartItemService cs;
	
	@Autowired
	UserService us;

	@PostMapping("/add/{pid}/{quantity}")
	public String addToCart(HttpServletResponse response, @PathVariable(value = "pid", required = true) String pid,
			@PathVariable(value = "quantity", required = true) String quantity,@AuthenticationPrincipal User user,@AuthenticationPrincipal CustomOAuth2User oath2) {
		int subTotal = 0;
		User loginUser = us.getLoginUser(user, oath2);
		if (loginUser == null) {
			return "You must login";
		} else {
			if (pid != null && quantity != null) {
				cs.addToCart(Integer.parseInt(pid), Integer.parseInt(quantity), loginUser);
			}
			return "Add to cart successfully!";
		}
	}

	@PostMapping("/delete/{pid}")
	public String deleteCart(HttpServletResponse response, @PathVariable(value = "pid", required = true) String pid,
			@AuthenticationPrincipal User user,@AuthenticationPrincipal CustomOAuth2User oath2) {
		Product product = ps.findById(Integer.valueOf(pid)).get();
		User loginUser = us.getLoginUser(user, oath2);
		cs.deleteByOrderAndProduct(null, product, loginUser);
		return "Delete successfully!";
	}

}

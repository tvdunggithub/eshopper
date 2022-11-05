package com.dungtran.eshopper.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dungtran.eshopper.config.CustomOAuth2User;
import com.dungtran.eshopper.entities.Product;
import com.dungtran.eshopper.entities.User;
import com.dungtran.eshopper.services.CartItemService;
import com.dungtran.eshopper.services.ProductService;
import com.dungtran.eshopper.services.UserService;
import com.dungtran.eshopper.url.CurrentUrl;

@Controller
public class ShopController {

	@Autowired
	ProductService ps;
	
	@Autowired
	CartItemService cs;
	
	@Autowired
	UserService us;

	@GetMapping("/{gender_category}")
	public String getAllProduct(Model model,@AuthenticationPrincipal User user,
			@AuthenticationPrincipal CustomOAuth2User oath2,
			@RequestParam(name = "keyword", required = false) String keyword,
			@PathVariable(name = "gender_category", required = true) String gc,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "startPrice", required = false) String startPrice,
			@RequestParam(name = "endPrice", required = false) String endPrice,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "flag", required = false) String flag) {
		User loginUser = us.getLoginUser(user, oath2);
		Pageable pageable = PageRequest.of(page.orElse(0), 9);
		String urlWithoutParam = CurrentUrl.addFirstParamString(gc, "", "");
		model.addAttribute("urlWithoutParam", urlWithoutParam);
		if (loginUser != null) {
			int countCart = cs.countByOrderAndUser(null, loginUser);
			model.addAttribute("cartSize", countCart);
		}
		if (startPrice == null) {
			if (keyword == null && category != null) {
				Page<Product> products = ps.findByCategory(gc, category, pageable);
				String url = CurrentUrl.addFirstParamString(gc, "category", category);
				String pageUrl = CurrentUrl.addFirstParamString(gc, "category", category);
				model.addAttribute("products", products);
				model.addAttribute("currentUrl", url);
				model.addAttribute("pageUrl", pageUrl);
				return "shop";
			} else if (keyword != null && category == null) {
				Page<Product> products = ps.findByNameAndGender(gc, keyword, pageable);
				String url = CurrentUrl.addFirstParamString(gc, "keyword", keyword);
				String pageUrl = CurrentUrl.addFirstParamString(gc, "keyword", keyword);
				model.addAttribute("products", products);
				model.addAttribute("currentUrl", url);
				model.addAttribute("pageUrl", pageUrl);
				return "shop";
			} else if (keyword == null && category == null) {
				Page<Product> products = ps.findByGenderCategory(gc, pageable);
				String url = CurrentUrl.getUrlWithoutParam(gc);
				String pageUrl = CurrentUrl.getUrlWithoutParam(gc);
				model.addAttribute("products", products);
				model.addAttribute("currentUrl", url);
				model.addAttribute("pageUrl", pageUrl);
				return "shop";
			} else
				return "error";

		} else {
			if (keyword == null && category != null) {
				Page<Product> products = ps.findByGenderCategoryPriceCategory(gc, category, Long.valueOf(startPrice),
						Long.valueOf(endPrice), pageable);
				String url = CurrentUrl.addFirstParamString(gc, "category", category);
				String pageUrl = CurrentUrl.addFirstParamString(gc, "category", category);
				model.addAttribute("products", products);
				model.addAttribute("currentUrl", url);
				pageUrl = CurrentUrl.addParamsString(pageUrl, "startPrice", startPrice);
				pageUrl = CurrentUrl.addParamsString(pageUrl, "endPrice", endPrice);
				model.addAttribute("pageUrl", pageUrl);
				System.out.println(url);
				return "shop";
			} else if (keyword != null && category == null) {
				Page<Product> products = ps.findByGenderCategoryPriceKeyword(gc, keyword, Long.valueOf(startPrice),
						Long.valueOf(endPrice), pageable);
				String url = CurrentUrl.addFirstParamString(gc, "keyword", keyword);
				String pageUrl = CurrentUrl.addFirstParamString(gc, "keyword", keyword);
				model.addAttribute("products", products);
				model.addAttribute("currentUrl", url);
				pageUrl = CurrentUrl.addParamsString(pageUrl, "startPrice", startPrice);
				pageUrl = CurrentUrl.addParamsString(pageUrl, "endPrice", endPrice);
				model.addAttribute("pageUrl", pageUrl);
				return "shop";
			} else if (keyword == null && category == null) {
				Page<Product> products = ps.findByGenderCategoryAndPrice(gc, Long.valueOf(startPrice),
						Long.valueOf(endPrice), pageable);
				String url = CurrentUrl.getUrlWithoutParam(gc);
				String pageUrl = CurrentUrl.getUrlWithoutParam(gc);
				model.addAttribute("products", products);
				model.addAttribute("currentUrl", url);
				pageUrl = CurrentUrl.addParamsString(pageUrl, "startPrice", startPrice);
				pageUrl = CurrentUrl.addParamsString(pageUrl, "endPrice", endPrice);
				model.addAttribute("pageUrl", pageUrl);
				return "shop";
			} else {
				return "error";
			}

		}
	}

	@GetMapping("/search")
	public String searchProduct(Model model,@AuthenticationPrincipal User user,
			@AuthenticationPrincipal CustomOAuth2User oath2,
			@RequestParam(name = "keyword", required = true) String keyword,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "startPrice", required = false) String startPrice,
			@RequestParam(name = "endPrice", required = false) String endPrice) {
		User loginUser = us.getLoginUser(user, oath2);
		Pageable pageable = PageRequest.of(page.orElse(0), 9);
		String url = CurrentUrl.addFirstParamString("search", "keyword", keyword);
		String pageUrl = CurrentUrl.addFirstParamString("search", "keyword", keyword);
		if (loginUser != null) {
			int countCart = cs.countByOrderAndUser(null, user);
			model.addAttribute("cartSize", countCart);
		}
		if (startPrice == null) {
			Page<Product> products = ps.findByName(keyword, pageable);
			model.addAttribute("currentUrl", url);
			model.addAttribute("pageUrl", pageUrl);
			model.addAttribute("products", products);
			return "shop";
		} else {
			Page<Product> products = ps.findByNameAndPrice(keyword, Long.valueOf(startPrice), Long.valueOf(endPrice), pageable);
			model.addAttribute("products", products);
			model.addAttribute("currentUrl", url);
			pageUrl = CurrentUrl.addParamsString(pageUrl, "startPrice", startPrice);
			pageUrl = CurrentUrl.addParamsString(pageUrl, "endPrice", endPrice);
			model.addAttribute("pageUrl", pageUrl);
			return "shop";
		}
	}
	
	
	//API
	
	@GetMapping("/api/{gender_category}")
	@ResponseBody
	public Page<Product> getPageProduct(@PathVariable(name = "gender_category", required = true) String gc,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "startPrice", required = false) String startPrice,
			@RequestParam(name = "endPrice", required = false) String endPrice,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "page", required = false) Optional<Integer> page){
		
		Pageable pageable = PageRequest.of(page.orElse(0), 9);
		
		if (startPrice == null) {
			if (keyword == null && category != null) {
				Page<Product> products = ps.findByCategory(gc, category, pageable);
				return products;
			} else if (keyword != null && category == null) {
				Page<Product> products = ps.findByNameAndGender(gc, keyword, pageable);
				return products;
			} else if (keyword == null && category == null) {
				Page<Product> products = ps.findByGenderCategory(gc, pageable);
				return products;
			} else
				return null;

		} else {
			if (keyword == null && category != null) {
				Page<Product> products = ps.findByGenderCategoryPriceCategory(gc, category, Long.valueOf(startPrice),
						Long.valueOf(endPrice), pageable);
				return products;
			} else if (keyword != null && category == null) {
				Page<Product> products = ps.findByGenderCategoryPriceKeyword(gc, keyword, Long.valueOf(startPrice),
						Long.valueOf(endPrice), pageable);
				return products;
			} else if (keyword == null && category == null) {
				Page<Product> products = ps.findByGenderCategoryAndPrice(gc, Long.valueOf(startPrice),
						Long.valueOf(endPrice), pageable);
				return products;
			} else {
				return null;
			}
		}
	}
	

}

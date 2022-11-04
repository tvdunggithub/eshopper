package com.dungtran.eshopper.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class ContactController {
	
	@GetMapping("/contact")
	public String getContact() {
		
		return "contactPage";
	}

}

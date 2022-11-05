package com.dungtran.eshopper.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dungtran.eshopper.entities.Mail;
import com.dungtran.eshopper.services.MailSevice;

@Controller
public class ContactController {
	
	@Autowired
	MailSevice ms;
	
	@GetMapping("/contact")
	public String getContact(@ModelAttribute("mail") Mail mail) {
		
		return "contact";
	}
	
	@PostMapping("/contact")
	public String sendMail(@Valid @ModelAttribute("mail") Mail mail,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/contact";
		}
		ms.sendEmail(mail);
		return "redirect:/contact";
	}

}

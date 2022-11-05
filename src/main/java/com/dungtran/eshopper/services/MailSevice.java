package com.dungtran.eshopper.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dungtran.eshopper.entities.Mail;

@Service
@Transactional
public interface MailSevice {
	
	public void sendEmail(Mail mail);

}

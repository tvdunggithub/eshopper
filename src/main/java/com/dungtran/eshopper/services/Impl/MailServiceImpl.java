package com.dungtran.eshopper.services.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dungtran.eshopper.entities.Mail;
import com.dungtran.eshopper.services.MailSevice;

@Service
@Transactional
public class MailServiceImpl implements MailSevice {

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendEmail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("eshopperco@gmail.com");		
		//set address to send mail
		message.setTo("tvdung1098@gmail.com");
		message.setText("From "+ mail.getEmail() + ":" +mail.getMessage());
		message.setSubject(mail.getName()+ "-" + mail.getSubject());
		
		mailSender.send(message);
		System.out.println("Mail sent successfully!");
		
	}

}

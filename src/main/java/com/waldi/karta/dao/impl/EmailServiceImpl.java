package com.waldi.karta.dao.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.waldi.karta.dao.EmailService;


@Service("userService")
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	  private Environment env;

	@Async
	public void sendEmail(SimpleMailMessage email) {
		System.out.println("Env = " + env.getProperty("spring.mail.usernam"));
		JavaMailSender mailSender = new JavaMailSenderImpl();
		
	    ((JavaMailSenderImpl) mailSender).setHost("localhost");
	    ((JavaMailSenderImpl) mailSender).setPort(25);
	     
	    ((JavaMailSenderImpl) mailSender).setUsername("wsparcie@localhost");
	    ((JavaMailSenderImpl) mailSender).setPassword("1234");
	     
	    Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		
		
		try {mailSender.send(email);							// nale�y doda� dependency : <groupId>javax.mail</groupId> <artifactId>mail</artifactId><version>1.4.7</version>
		
		}catch (Exception e) {
			System.out.println("Blad z getUserInfo to: "+ e.toString());
		}
		
	}

}

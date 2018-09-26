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
		System.out.println("Aplikation spring.mail = " + env.getProperty("spring.mail.password"));
		JavaMailSender mailSender = new JavaMailSenderImpl();
		
//	    ((JavaMailSenderImpl) mailSender).setHost("localhost");
		((JavaMailSenderImpl) mailSender).setHost(env.getProperty("spring.mail.host"));
//	    ((JavaMailSenderImpl) mailSender).setPort(25);
		((JavaMailSenderImpl) mailSender).setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
//	     
//	    ((JavaMailSenderImpl) mailSender).setUsername("wsparcie@localhost");
		((JavaMailSenderImpl) mailSender).setUsername(env.getProperty("spring.mail.username"));
//	    ((JavaMailSenderImpl) mailSender).setPassword("1234");
		((JavaMailSenderImpl) mailSender).setPassword(env.getProperty("spring.mail.password"));
	     
	    Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		
		
		try {mailSender.send(email);							// nale¿y dodaæ dependency : <groupId>javax.mail</groupId> <artifactId>mail</artifactId><version>1.4.7</version>
		
		}catch (Exception e) {
			System.out.println("Blad z getUserInfo to: "+ e.toString());
		}
		
	}

}

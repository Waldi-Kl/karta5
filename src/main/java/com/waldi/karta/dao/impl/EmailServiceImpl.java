package com.waldi.karta.dao.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.waldi.karta.dao.EmailService;


@Service("userService")
public class EmailServiceImpl implements EmailService {
	
	//@Autowired
	//private JavaMailSender mailSender;

	@Async
	public void sendEmail(SimpleMailMessage email) {
		JavaMailSender mailSender = new JavaMailSenderImpl();
		System.out.println("Uruchomi³ siê - sendEmail");
		System.out.println("email to :" + mailSender.toString());
		try {mailSender.send(email);							// nale¿y dodaæ dependency : <groupId>javax.mail</groupId> <artifactId>mail</artifactId><version>1.4.7</version>
		
		}catch (Exception e) {
			System.out.println("Blad z getUserInfo to: "+ e.toString());
		}
		
	}

}

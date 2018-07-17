package com.waldi.karta.dao.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.waldi.karta.dao.EmailService;


@Service("userService")
public class EmailServiceImpl implements EmailService {
	
	//@Autowired
	private JavaMailSender mailSender;

	@Async
	public void sendEmail(SimpleMailMessage email) {
		//mailSender.send(email);							// nale�y doda� dependency : <groupId>javax.mail</groupId> <artifactId>mail</artifactId><version>1.4.7</version>
		System.out.println("Problem z email - powstaje NULL");
	}

}

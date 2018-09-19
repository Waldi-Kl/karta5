package com.waldi.karta.dao.impl;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.waldi.karta.dao.EmailService;


@Service("userService")
public class EmailServiceImpl implements EmailService {

	@Async
	public void sendEmail(SimpleMailMessage email) {
		JavaMailSender mailSender = new JavaMailSenderImpl();
		
	    ((JavaMailSenderImpl) mailSender).setHost("poczta.interia.pl");
	    ((JavaMailSenderImpl) mailSender).setPort(465);
	     
	    ((JavaMailSenderImpl) mailSender).setUsername("waldi_kl@interia.pl");
	    ((JavaMailSenderImpl) mailSender).setPassword("waldi_kl11");
	     
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

package com.waldi.karta.dao;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	public void sendEmail(SimpleMailMessage email);
	public void test();

}

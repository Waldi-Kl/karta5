package com.waldi.karta.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waldi.karta.dao.EmailService;
import com.waldi.karta.dao.UserInfoDAO;
import com.waldi.karta.dao.impl.EmailServiceImpl;
import com.waldi.model.UserInfo;

@Controller
@RequestMapping(value = "/userInfo")
public class PassworldController {
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	
//	@Autowired
//	private UserService userService;

	//@Autowired
	private EmailService emailService;
	private EmailServiceImpl emailService2;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;	// kodowanie hasÂ³a rypt-em

	
	// Display forgotPassword page
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView displayForgotPasswordPage() {
		ModelAndView model = new ModelAndView("forgotPassword");
		model.addObject("message", "OK.");
		model.addObject("title", "UserInfo");
		return model;
		//return new ModelAndView("forgotPassword");
    }
  
    // Process form submission from forgotPassword page
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {
		System.out.println("Poszed³ POST w forgot");
		UserInfo user = new UserInfo();
		user = userInfoDAO.findUserByEmail(userEmail);
		modelAndView.addObject("title", "UserInfo");
		if (user==null) {
			//System.out.println("null na usser");
			modelAndView.addObject("errorMessage", "Nie znaleziono konta przypisaneg do podanego agresu.");
		} else {
		
			// Generate random 36-character string token for reset password 
			//UserInfoDAO user = optional.get();
			user.setResetToken(UUID.randomUUID().toString());
			//System.out.println("User Rest Token: "+ user.getResetToken());
			// Save token to database
			userInfoDAO.save(user);

			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");
			System.out.println("Muj email: "+ user.getEmail());
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ "/reset?token=" + user.getResetToken());
			emailService2 = new EmailServiceImpl();
			System.out.println("emailService: "+ emailService2.toString());
			//emailService.sendEmail(passwordResetEmail);
			
			emailService2.sendEmail(passwordResetEmail);

			// Add success message to view
			modelAndView.addObject("successMessage", "Link do zmiany has³a zosta³ przes³any na adres: " + userEmail);
		}

		//System.out.println("Powinien uruchomiæ ksiê - forgotPassword");
		modelAndView.setViewName("forgotPassword");
		return modelAndView;

	}

   
    // Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		System.out.println("Uruchomil siê EXCeption!!");
		return new ModelAndView("redirect:login");
	}
}

package com.waldi.karta.controller;

import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.waldi.karta.dao.EmailService;
import com.waldi.karta.dao.UserInfoDAO;
import com.waldi.karta.dao.impl.EmailServiceImpl;
import com.waldi.model.UserInfo;

@Controller
@RequestMapping(value = "/forgotpassword")
public class PassworldController {
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	
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
		//System.out.println("Poszed³ POST w forgot");
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

			String appUrl = request.getScheme() + "://" + request.getServerName() + ":8080/Karta5/forgotpassword";
			
			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("wsparcie@localhost"); // qwer
			//System.out.println("Mój email: "+ user.getEmail());
			passwordResetEmail.setTo(user.getEmail());
			passwordResetEmail.setSubject("Restart has³a");
			passwordResetEmail.setText("Aby zrestartowaæ has³o kliknij lunk poni¿ej:\n" + appUrl
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

	// Display form to reset password
		@RequestMapping(value = "/reset", method = RequestMethod.GET)
		public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {
			
			UserInfo user = new UserInfo();
			user = userInfoDAO.findUserByResetToken(token);
			modelAndView.addObject("title", "Reset");
			if (user!=null) { // Token found in DB
				modelAndView.addObject("resetToken", token);
				modelAndView.setViewName("resetPassword");
				
			} else { // Token not found in DB
				modelAndView.addObject("errorMessage", "Oops!  Niepoprawny reset link.");
				modelAndView.setViewName("forgotPassword");
			}			
			return modelAndView;
		}
		
		// Process reset password form
		@RequestMapping(value = "/reset", method = RequestMethod.POST)
		public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

			//System.out.println("User Rest Token: "+ requestParams.get("resetToken"));
			// Find the user associated with the reset token
			UserInfo user = new UserInfo();
			user = userInfoDAO.findUserByResetToken(requestParams.get("resetToken"));
			
			// This should always be non-null but we check just in case
			if (user != null) {
				// Set new password    
				user.setPass(requestParams.get("password"));
	            
				// Set the reset token to null so it cannot be used again
				user.setResetToken(null);

				// Save user
				userInfoDAO.save(user);

				// In order to set a model attribute on a redirect, we must use
				// RedirectAttributes

				modelAndView.setViewName("loginPage");
				return modelAndView;
				
			} else {
				modelAndView.addObject("errorMessage", "Oops!  Niew³aœciwy reset link.");
				modelAndView.setViewName("resetPassword");	
			}
			
			return modelAndView;
	   }
   
    // Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		System.out.println("Uruchomil siê EXCeption!!");
		return new ModelAndView("loginPage");
	}
}

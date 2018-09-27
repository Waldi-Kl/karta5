package com.waldi.karta.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waldi.karta.dao.UserInfoDAO;
import com.waldi.karta.dao.impl.UserInfoDAOImpl;
import com.waldi.model.UserInfo;


@Controller
public class MainController {
	
	@Autowired
	private UserInfoDAO userInfoDAO;
	
	@Autowired
	  private Environment env;

   @RequestMapping(value = { "/", "/homepage"}, method = RequestMethod.GET)
   public String mainController(Model model) {
       model.addAttribute("title", "Welcome");
       model.addAttribute("message", "Wybierz rodzaj zadania.");

       return "homePage";
   }


 
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String loginPage(Model model ) {
	   model.addAttribute("title", "Login");
       return "loginPage";
   }
   
   @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
   public String forgotPassword(Model model ) {
	   model.addAttribute("title", "Forgot");
       return "forgotPassword";
   }
 
   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
       model.addAttribute("title", "Logout");
       model.addAttribute("message", "Do zobaczenia.");
       return "logoutSuccessfulPage";
   }

   @RequestMapping(value = "/403", method = RequestMethod.GET)
   public String accessDenied(Model model, Principal principal) {
	   model.addAttribute("title", "403"); 
       if (principal != null) {
           model.addAttribute("message", "Witaj " + principal.getName()
                   + "<br> Nie posiadasz uprawni� do tej strony!");
       } else {
           model.addAttribute("msg",
                   "Nie posiadasz uprawni� do tej strony!");
       }
       return "403Page";
   }
   
// ---------------- Brak podania loginu na stronie do logowania --------------
   @RequestMapping(value = {"/newpass"}, method = RequestMethod.GET)
   public String newPasswordErr(Model model) {
       model.addAttribute("title", "Login");
       model.addAttribute("message", "Podaj login.");
       return "loginPage";
   }
   // ---------------- Informacja o zmianie chas�� -------------- 
   @RequestMapping(value = {"/newpass/update"}, method = RequestMethod.POST)
   public String newPasswordUpdate(Model model) {
	  // System.out.println("uruchomi� sie updat");
       model.addAttribute("title", "Karta w�dkarska");
       model.addAttribute("message", "Zmieniono has�o.");
       return "homePage";
   }
   // ---------------- Metoda generuj�ca link do zmiany chas�a  --------------
   @RequestMapping(value = {"/newpass/{userLog}"}, method = RequestMethod.GET)
   public String newPassword(Model model, @PathVariable("userLog") String userLog) {
	
	 // UserInfo myUser = userInfoDAO.getUserInfo(userLog);
	  
	   userInfoDAO.linkToPassChange(userLog);
       model.addAttribute("title", "Karta w�dkarska");
       model.addAttribute("message", "Na tw�j e-mal przes�ano link do zmiany has��.");
       return "newPass";
   }
   // ---------------- Metoda pozwalaj�ca zmieni� has�o. Link z email  -------------- 
   @RequestMapping(value = {"/newpass/{userLog}/{idChange}"}, method = RequestMethod.GET)
   public String passChange(Model model, @PathVariable("userLog") String userLog, @PathVariable("idChange") int idChange) {
	   
       model.addAttribute("title", "Karta w�dkarska");
       return "newPass2";
   }
   

}

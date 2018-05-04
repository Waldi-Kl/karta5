package com.waldi.karta.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waldi.karta.dao.impl.UserInfoDAOImpl;


@Controller
public class MainController {

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
                   + "<br> Nie posiadasz uprawniê do tej strony!");
       } else {
           model.addAttribute("msg",
                   "Nie posiadasz uprawniê do tej strony!");
       }
       return "403Page";
   }
   

}

package com.waldi.karta.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/userInfo")
public class UserController {
	
	 @RequestMapping(method = RequestMethod.GET)
	   public String userInfo(Model model, Principal principal) {
	 
	       // After user login successfully.
	       String userName = principal.getName();
	 
	       model.addAttribute("title", "UserInfo");
	 
	       return "userInfoPage";
	   }

}

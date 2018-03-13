package com.waldi.karta.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/cardInfo")
public class CardController {

	 @RequestMapping(value = "/fishing",method = RequestMethod.GET)
	   public String fishingInfo(Model model, Principal principal) {
	 
	       model.addAttribute("title", "Wêdkarstwo");
	       return "kartaW";
	   }
	 
	 @RequestMapping(value = "/hunting",method = RequestMethod.GET)
	   public String huntingInfo(Model model, Principal principal) {
	 
	       model.addAttribute("title", "£owiectwo");
	       return "kartaL";
	   }
	 
	 @RequestMapping(value = "/guarding",method = RequestMethod.GET)
	   public String guardingInfo(Model model, Principal principal) {
	 
	       model.addAttribute("title", "£owiectwo");
	       return "kartaS";
	   }
}

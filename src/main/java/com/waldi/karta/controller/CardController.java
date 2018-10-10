package com.waldi.karta.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waldi.karta.dao.PdfCreator;
import com.waldi.karta.dao.UserInfoDAO;

@Controller
@RequestMapping(value = "/cardInfo")
public class CardController {
	
	@Autowired
	private PdfCreator pdfDoc;
	
//	public CardController() {
//		System.out.println("Uruchomi� sie CardController");
//	}

	 @RequestMapping(value = "/fishing",method = RequestMethod.GET)
	   public String fishingInfo(Model model, Principal principal) {
	 
	       model.addAttribute("title", "W�dkarstwo");
	       return "kartaW";
	   }
	 
	 @RequestMapping(value = "/hunting",method = RequestMethod.GET)
	   public String huntingInfo(Model model, Principal principal) {
	 
	       model.addAttribute("title", "�owiectwo");
	       return "kartaL";
	   }
	 
	 @RequestMapping(value = "/guarding",method = RequestMethod.GET)
	   public String guardingInfo(Model model, Principal principal) {
	 
	       model.addAttribute("title", "�owiectwo");
	       return "kartaS";
	   }
	 
	 @RequestMapping(value = "/guarding/pdf",method = RequestMethod.GET)
	   public String guardingPdf(Model model, Principal principal) {
	 
	       model.addAttribute("title", "Posz�o");
	       pdfDoc.printPdf();
	       return "kartaS";
	   }
	 

}

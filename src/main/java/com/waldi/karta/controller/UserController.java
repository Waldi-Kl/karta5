package com.waldi.karta.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.SQLError;
import com.waldi.karta.dao.UserInfoDAO;
import com.waldi.karta.dao.impl.UserInfoDAOImpl;
import com.waldi.model.UserInfo;

@Controller
@RequestMapping(value = "/userInfo")
public class UserController {
	
	@Autowired
    private UserInfoDAO userInfoDAO;

	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// After user login successfully.
		//String userName = principal.getName();
		model.addAttribute("title", "UserInfo");
		return "userInfoPage";
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String MainController(Model model){		
		List<UserInfo> list = null;		
		try{
			list = userInfoDAO.getUsersList();
		} catch (Exception e){
			// Tu powinno znalesc sie obs�uga wielu b��d�w jdbc (e.get..())
			model.addAttribute("title", "UserInfo");
			model.addAttribute("message", "Nie powodzenie podczas po��czenia do bazy!");
			return "403Page";
		}

		model.addAttribute("title", "UserInfo");
		model.addAttribute("users", list);
		return "userListPage";
	}
	

	@RequestMapping(value = { "/adduser" }, method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("title", "UserAdd");
		List<UserInfo> list = userInfoDAO.getUsersList();
		model.addAttribute("users", list);
		return "userListPage";
	}
	
	@RequestMapping(value = { "/adduser" }, method = RequestMethod.POST)
	public String putUser(Model model) {
		model.addAttribute("title", "UserInfo");
		List<UserInfo> list = userInfoDAO.getUsersList();
		model.addAttribute("users", list);
		return "userAddPage";
	}
}

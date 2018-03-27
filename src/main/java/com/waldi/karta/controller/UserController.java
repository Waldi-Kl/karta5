package com.waldi.karta.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waldi.karta.dao.UserInfoDAO;
import com.waldi.model.UserInfo;

@Controller
@RequestMapping(value = "/userInfo")
public class UserController {
	
	@Autowired
    private UserInfoDAO userInfoDAO;

	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public String UserInfo(Model model, Principal principal) {

		// After user login successfully.
		//String userName = principal.getName();
		model.addAttribute("title", "UserInfo");
		return "userInfoPage";
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String UserListController(Model model){		
		List<UserInfo> list = null;		
		try{
			list = userInfoDAO.getUsersList();
		} catch (Exception e){
			// Tu powinno znalesc sie obs³uga wielu b³êdów jdbc (e.get..())
			model.addAttribute("title", "UserInfo");
			model.addAttribute("message", "Nie powodzenie podczas po³¹czenia do bazy!");
			return "403Page";
		}

		model.addAttribute("title", "UserInfo");
		model.addAttribute("users", list);
		return "userListPage";
	}
	

	@RequestMapping(value = { "/adduser" }, method = RequestMethod.POST)
	public String AddUser(Model model,  UserInfo newUser) {
		System.out.println("Send Money::" + newUser.getLogin());
		model.addAttribute("title", "UserAdd");
		List<UserInfo> list = userInfoDAO.getUsersList();
		model.addAttribute("users", list);
		return "userListPage";
	}
	
	/**
	@RequestMapping(value = { "/updateuser" }, method = RequestMethod.POST)
	public String putUser(Model model) {
		model.addAttribute("title", "UserInfo");
		List<UserInfo> list = userInfoDAO.getUsersList();
		model.addAttribute("users", list);
		return "userAddPage";
	}
	**/
}

package com.waldi.karta.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waldi.karta.dao.UserInfoDAO;
import com.waldi.model.UserInfo;

@Controller
@RequestMapping(value = "/userInfo")
public class UserController {
	
	@Autowired
    private UserInfoDAO userInfoDAO;

	@RequestMapping(value = { "/user/{userLog}" }, method = RequestMethod.GET)
	public String userInfo(Model model, @PathVariable("userLog") String userLog) {

		UserInfo userInf = userInfoDAO.getUserInfo(userLog);
		model.addAttribute("title", "UserInfo");
		model.addAttribute("message", userLog);
		model.addAttribute("user", userInf);
		return "userInfoPage";
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String userListController(Model model){		
		List<UserInfo> list = null;		
		try{
			list = userInfoDAO.getUsersList();
		} catch (Exception e){
			// Tu powinno znalesc sie obs³uga wielu b³êdów jdbc (e.get..())  UWAGA!!!! powinny tworzyæ siê logi z b³êdami.
			model.addAttribute("title", "UserInfo");
			model.addAttribute("message", "Nie powodzenie podczas po³¹czenia do bazy!");
			return "403Page";
		}

		model.addAttribute("title", "UserInfo");
		model.addAttribute("users", list);
		return "userListPage";
	}
	

	@RequestMapping(value = { "/adduser" }, method = RequestMethod.POST)
	public String addUser(Model model,  UserInfo newUser) {
		model.addAttribute("title", "UserInfo");
		if (newUser.getLogin().equals("")){
			model.addAttribute("message", "Problem: brak loginu.");
			return "403Page";
		}

		try{
		userInfoDAO.insertUser(newUser);
		} catch (Exception e){
			// Tu powinno znalesc sie obs³uga wielu b³êdów jdbc (e.get..())  UWAGA!!!! powinny tworzyæ siê logi z b³êdami.			
			model.addAttribute("message", "Problem: "+ e.getCause());
			return "403Page";
		}
		List<UserInfo> list = userInfoDAO.getUsersList();
		model.addAttribute("users", list);
		return "userListPage";
	}
	
	@RequestMapping(value = { "/deleteuser" }, method = RequestMethod.POST)
	public String deleteUser(Model model,  UserInfo newUser) {
		
		
		model.addAttribute("title", "UserInfo");
		try{
		userInfoDAO.insertUser(newUser);
		} catch (Exception e){
			// Tu powinno znalesc sie obs³uga wielu b³êdów jdbc (e.get..())  UWAGA!!!! powinny tworzyæ siê logi z b³êdami.			
			model.addAttribute("message", "Problem: "+ e.getCause());
			return "403Page";
		}
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

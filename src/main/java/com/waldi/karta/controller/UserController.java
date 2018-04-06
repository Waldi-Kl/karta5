package com.waldi.karta.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
		List<String> userRoles= userInfoDAO.getUserRoles(userLog);
		if (userRoles.size()==0) userRoles.add("Brak przypisanej roli");
		UserInfo userInf = userInfoDAO.getUserInfo(userLog);
		model.addAttribute("title", "UserInfo");
		//model.addAttribute("message", userLog);
		model.addAttribute("user", userInf);
		model.addAttribute("role", userRoles);
		return "userInfoPage";
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String userListController(Model model){		
		List<UserInfo> list = null;		
		try{
			list = userInfoDAO.getUsersList();
		} catch (Exception e){
			// Tu powinno znalesc sie obs�uga wielu b��d�w jdbc (e.get..())  UWAGA!!!! powinny tworzy� si� logi z b��dami.
			model.addAttribute("title", "UserInfo");
			model.addAttribute("message", "Nie powodzenie podczas po��czenia do bazy!");
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
			// Tu powinno znalesc sie obs�uga wielu b��d�w jdbc (e.get..())  UWAGA!!!! powinny tworzy� si� logi z b��dami.			
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
			// Tu powinno znalesc sie obs�uga wielu b��d�w jdbc (e.get..())  UWAGA!!!! powinny tworzy� si� logi z b��dami.			
			model.addAttribute("message", "Problem: "+ e.getCause());
			return "403Page";
		}
		List<UserInfo> list = userInfoDAO.getUsersList();
		model.addAttribute("users", list);
		return "userListPage";
		
	}
	
	@RequestMapping(value = { "/updateuser" }, method = RequestMethod.POST)
	public String putUser(Model model,  UserInfo myUser) throws IOException {
		model.addAttribute("title", "UserInfo");
// ------------------------ To jest zmiana kodowania z tekstu przesy�anego z formulzrza -------------------------------------
		  String newSName = myUser.getSurname();
		  newSName = URLEncoder.encode( myUser.getSurname(), "ISO-8859-1" ); // H%C3%A9l%C3%A8ne
		  newSName = URLDecoder.decode( newSName, "UTF-8" );
		  System.out.println("Po kodowaniu :"+ newSName);
		  myUser.setSurname(newSName);
		  
		  String newName = myUser.getName();
		  newName = URLEncoder.encode( myUser.getName(), "ISO-8859-1" ); // H%C3%A9l%C3%A8ne
		  newSName = URLDecoder.decode( newName, "UTF-8" );
		  System.out.println("Po kodowaniu :"+ newName);
		  myUser.setName(newName);
		  
		  String newEmail = myUser.getEmail();
		  newEmail = URLEncoder.encode( myUser.getEmail(), "ISO-8859-1" ); // H%C3%A9l%C3%A8ne
		  newEmail = URLDecoder.decode( newEmail, "UTF-8" );
		  System.out.println("Po kodowaniu :"+ newEmail);
		  myUser.setEmail(newEmail);
		  
		  
		try{
		userInfoDAO.updateUser(myUser);
		} catch (Exception e){
			// Tu powinno znalesc sie obs�uga wielu b��d�w jdbc (e.get..())  UWAGA!!!! powinny tworzy� si� logi z b��dami.			
			model.addAttribute("message", "Problem: "+ e.getCause());
			return "403Page";
		}
		List<UserInfo> list = userInfoDAO.getUsersList();
		model.addAttribute("users", list);
		return "userListPage";
	}
	
	@RequestMapping(value = { "/updaterole" }, method = RequestMethod.POST)
	public String putUserRole(Model model) {
		model.addAttribute("title", "UserInfo");

		List<UserInfo> list = userInfoDAO.getUsersList();
		model.addAttribute("users", list);
		return "userListPage";
	}
}

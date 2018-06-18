package com.waldi.karta.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.waldi.karta.dao.UserInfoDAO;
import com.waldi.model.UserInfo;

@Service
public class KardDBAuthenticationService implements UserDetailsService{ 

	@Autowired
    private UserInfoDAO userInfoDAO;
 
    
    public UserDetails loadUserByUsername(String userlogin) throws UsernameNotFoundException {
    	UserInfo userInfo=null;
    	try{
    		userInfo = userInfoDAO.getUserInfo(userlogin);
		} catch (Exception e){
			// Tu powinno znalesc sie obs³uga wielu b³êdów jdbc (e.get..())  UWAGA!!!! powinny tworzyæ siê logi z b³êdami.

			System.out.println("Posz³o: getUserIngo=NULL");
		}
        //UserInfo userInfo = userInfoDAO.getUserInfo(userlogin);
        //System.out.println("User to :"+ userInfo.getSurname());
      
        if (userInfo == null) {
        	System.out.println("loadUserByUsername Author... ?");
            throw new UsernameNotFoundException("User " + userlogin + " was not found in the database");
        }
         
        // [USER,ADMIN,..]
        List<String> roles= userInfoDAO.getUserRoles(userlogin);
         
        
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        if(roles!= null)  {
            for(String role: roles)  {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantList.add(authority);
            }
        }       

        UserDetails userDetails = null;
//        if (!userInfo.equals(null)) {
//    	   //UserDetails userDetails = (UserDetails) new User(userInfo.getName(), //
//        	userDetails = (UserDetails) new User(userInfo.getName(), //
//    			   userInfo.getPass(),grantList);    
//        }
        try{
        	userDetails = (UserDetails) new User(userInfo.getName(), //
     			   userInfo.getPass(),grantList);
        } catch (Exception e){
			// Tu powinno znalesc sie obs³uga wielu b³êdów jdbc (e.get..())  UWAGA!!!! powinny tworzyæ siê logi z b³êdami.

			System.out.println("To jest b³¹d przy nieistniej¹cym uzytkowniku");
			//System.out.println("e."+e);
		}
 
        return userDetails; // to przechodzi do zmiennej "userPrincipal"
    }

}

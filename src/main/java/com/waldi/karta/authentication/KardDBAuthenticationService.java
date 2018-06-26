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
			// Tu powinno znalesc sie obs�uga wielu b��d�w jdbc (e.get..())  UWAGA!!!! powinny tworzy� si� logi z b��dami.
			// Ale prawdopodobnie nie pojawi si� tu b��d.
			System.out.println("Posz�o: getUserIngo=NULL");
		}

        if (userInfo.getName() == null) {
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

        try{
        	userDetails = (UserDetails) new User(userInfo.getName(), //
     			   userInfo.getPass(),grantList);
        } catch (Exception e){
			// Tu powinno znalesc sie obs�uga wielu b��d�w jdbc (e.get..())  UWAGA!!!! powinny tworzy� si� logi z b��dami.
        	// Tu b��d nie powinien si� pojawi�
			System.out.println("To jest b��d przy nieistniej�cym uzytkowniku");

		}
 
        return userDetails; // to przechodzi do zmiennej "userPrincipal"
    }

}

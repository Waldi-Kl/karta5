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
        UserInfo userInfo = userInfoDAO.getUserInfo(userlogin);

        if (userInfo == null) {
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
         
        UserDetails userDetails = (UserDetails) new User(userInfo.getName(), //
                userInfo.getPass(),grantList);
 
        return userDetails; // to przechodzi do zmiennej "userPrincipal"
    }

}

package com.waldi.karta.dao;

import java.util.List;

import com.waldi.model.UserInfo;

public interface UserInfoDAO {

  //  public UserInfo getUserInfo(String userName);
    public UserInfo getUserInfo(String userLogin);
    
    // [USER,AMIN,..]
    public List<String> getUserRoles(String userName);
     

}

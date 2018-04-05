package com.waldi.karta.dao;

import java.util.List;

import com.waldi.model.UserInfo;

public interface UserInfoDAO {

  //  public UserInfo getUserInfo(String userName);
    public UserInfo getUserInfo(String userLogin);
    
    // [USER,AMIN,..]
    public List<String> getUserRoles(String userName);
    
    public List<UserInfo> getUsersList();
    
    public void insertUser(UserInfo u);
    
    public void deleteUser(String userLogin);
    public void updateUser(UserInfo user);
     

}

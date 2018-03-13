package com.waldi.karta.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.waldi.model.UserInfo;

public class UserInfoMapper implements RowMapper<UserInfo> {

	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int userId = rs.getInt("id");
		String surname = rs.getString("surname");
		String userName = rs.getString("name");
		String userLogin = rs.getString("login");
        String password = rs.getString("pass");        
        String email = rs.getString("e-mail"); 
 
        UserInfo user = new UserInfo(surname, password);
        user.setName(userName);
        user.setLogin(userLogin);
        user.setId(userId);
        user.setEmail(email);
        
        return user;
	}

}

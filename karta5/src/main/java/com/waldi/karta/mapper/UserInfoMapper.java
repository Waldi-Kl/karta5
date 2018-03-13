package com.waldi.karta.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.waldi.model.UserInfo;

public class UserInfoMapper implements RowMapper<UserInfo> {

	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String userLogin = rs.getString("login");
        String password = rs.getString("pass");
        String surname = rs.getString("surname");
        String name = rs.getString("name");
 
        UserInfo user = new UserInfo(surname, password);
        user.setName(name);
        user.setLogin(userLogin);
        
        return user;
	}

}

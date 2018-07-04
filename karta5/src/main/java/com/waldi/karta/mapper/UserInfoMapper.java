package com.waldi.karta.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.waldi.model.UserInfo;

public class UserInfoMapper implements RowMapper<UserInfo> {
	
	public static final String BASE_SQL = //
			 "Select u.id, u.surname, u.name, u.login, u.pass, u.email "//
			+ " from user u ";

	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub

		int userId = rs.getInt("id");
		String surname = rs.getString("surname");
		String userName = rs.getString("name");
		String userLogin = rs.getString("login");
        String password = rs.getString("pass");        
        String email = rs.getString("email"); 
 
        UserInfo user = new UserInfo(surname, password);
        user.setName(userName);
        user.setLogin(userLogin);
        user.setId(userId);
        user.setEmail(email);
        return user;

	}

	
}

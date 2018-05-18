package com.waldi.karta.dao.impl;

import java.io.BufferedReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waldi.karta.dao.UserInfoDAO;
import com.waldi.karta.mapper.UserInfoMapper;
import com.waldi.model.UserInfo;

@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {

	@Autowired
	public UserInfoDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<String> getUserRoles(String userLogin) {
		// String sql = "Select r.User_Role "//
		// + " from User_Roles r where r.Username = ? ";
		String sql = "select r.rule from rule r where r.id in (select ur.rule_id "//
				+ "from user_rule ur where ur.user_id =(select u.id from user u where u.login =?))";
		Object[] params = new Object[] { userLogin };
		List<String> rules = this.getJdbcTemplate().queryForList(sql, params, String.class);

		return rules;
	}

	public UserInfo getUserInfo(String userLogin) {
		// TODO Auto-generated method stub
		// String sql = "Select * "// .id, u.surname, u.name, u.login, u.pass, u.e-mail
		// + " from user u where u.login = ? ";
		String sql = UserInfoMapper.BASE_SQL + "where u.login = ?";
		Object[] params = new Object[] { userLogin };
		UserInfoMapper mapper = new UserInfoMapper();
		UserInfo userInfo = new UserInfo();
		try {
			userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		}catch (Exception e) {
			System.out.println("B³¹d z getUserInfo to: "+ e.toString());
		}
		
		return userInfo;

	}

	
	public List<UserInfo> getUsersList(){

		String sql = UserInfoMapper.BASE_SQL;
		Object[] params = new Object[] {};
		UserInfoMapper mapper = new UserInfoMapper();
		List<UserInfo> lista = this.getJdbcTemplate().query(sql, params, mapper);
		return  lista;
	}
	
	public void insertUser(UserInfo user){		
		String sql = "INSERT INTO user " +
			"(surname, name , login , pass , email) VALUE (?,?,?,?,?)";		
		this.getJdbcTemplate().update(sql, user.getSurname(),user.getName(),user.getLogin(),user.getPass(),user.getEmail());
	}
	
	public void deleteUser(String userLogin) {
		String sql = "DELETE FROM user WHERE login=?";
		this.getJdbcTemplate().update(sql, userLogin.trim());
	}

	public void updateUser(UserInfo user) {
		//System.out.println("Nazwisko z updateUser :"+ user.getSurname());
		String sql = "UPDATE user " +
		"SET surname = ?, name= ?, email= ? WHERE id = ?";
		this.getJdbcTemplate().update(sql,  user.getSurname(), user.getName(),user.getEmail(),user.getId());
		
	}

	public void updateRule(int rule, int idUser) {
		// TODO Auto-generated method stub

		String sql = "SELECT EXISTS (SELECT 1 from user_rule WHERE user_id=?)";
		String sql1 = "update user_rule SET rule_id= ? WHERE user_id= ?";		
		String sql2 = "INSERT INTO user_rule " +
						"(user_id, rule_id) VALUE (?,?)";	
		String sql3 = "delete from user_rule where user_id =?";
		String sw1 = (String)getJdbcTemplate().queryForObject(
				sql, new Object[] { idUser }, String.class);
		if (sw1.equalsIgnoreCase("0")) {						// sta³a powinna byæ zapisana nie w formie liczbowej !!!!!
			this.getJdbcTemplate().update(sql2,  idUser, rule);
		} else {
			if (rule==0) {
				this.getJdbcTemplate().update(sql3, idUser);
			} else {
				this.getJdbcTemplate().update(sql1, rule, idUser);
			}
		}
		
	}

	@Override
	public void deleteRule(int idUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passChange(int idUser, int idChange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void linkToPassChange(String userLog) {
		// TODO Auto-generated method stub
		 System.out.println("Wys³ano link do urzytkownika");
		 
	}

}

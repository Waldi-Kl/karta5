package com.waldi.karta.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
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

	// public UserInfo findUserInfo(String userName) {
	// String sql = "Select u.login,u.pass "//
	// + " from user u where u.login = ? ";
	//
	// Object[] params = new Object[] { userName };
	// UserInfoMapper mapper = new UserInfoMapper();
	// try {
	// UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params,
	// mapper);
	// return userInfo;
	// } catch (EmptyResultDataAccessException e) {
	// return null;
	// }
	// }

	public List<String> getUserRoles(String userLogin) {
		// String sql = "Select r.User_Role "//
		// + " from User_Roles r where r.Username = ? ";
		String sql = "select r.rule from rule r where r.id in (select ur.rule_id "//
				+ "from user_rule ur where ur.user_id =(select u.id from user u where u.login =?))";

		Object[] params = new Object[] { userLogin };

		List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

		return roles;
	}

	public UserInfo getUserInfo(String userLogin) {
		// TODO Auto-generated method stub
		String sql = "Select u.login,u.pass,u.surname,u.name "//
				+ " from user u where u.login = ? ";

		Object[] params = new Object[] { userLogin };
		UserInfoMapper mapper = new UserInfoMapper();
		try {
			UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

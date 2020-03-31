package com.spring.boot.rocks.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.boot.rocks.model.AppUser;

public class AppUserRowMapper implements RowMapper<AppUser> {

	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppUser appuser = new AppUser();

		appuser.setId(rs.getLong("id"));
		appuser.setUsername(rs.getString("username"));
		appuser.setPassword(rs.getString("password"));
		appuser.setUseremail(rs.getString("useremail"));
		appuser.setUserfirstname(rs.getString("userfirstname"));
		appuser.setUserlastname(rs.getString("userlastname"));
		appuser.setUseraddress(rs.getString("useraddress"));
		appuser.setRoles(appuser.getRoles());
		return appuser;
	}

}
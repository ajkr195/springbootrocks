package com.spring.boot.rocks.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.rocks.model.AppUser;

@Transactional
@Repository
public class AppUserDaoImpl implements AppUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AppUser> getAllAppUsers() {
		String query = "SELECT * from app_user";
		RowMapper<AppUser> rowMapper = new AppUserRowMapper();
		List<AppUser> list = jdbcTemplate.query(query, rowMapper);

		return list;
	}
}
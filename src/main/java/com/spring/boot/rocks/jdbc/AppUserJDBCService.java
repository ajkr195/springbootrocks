package com.spring.boot.rocks.jdbc;

import java.util.List;

import com.spring.boot.rocks.model.AppUser;

public interface AppUserJDBCService {
	public List<AppUser> getAllAppUsers();
}
package com.spring.boot.rocks.jdbc;

import java.util.List;

import com.spring.boot.rocks.model.AppUser;

public interface AppUserDao {
 
 public List<AppUser> getAllAppUsers();
}
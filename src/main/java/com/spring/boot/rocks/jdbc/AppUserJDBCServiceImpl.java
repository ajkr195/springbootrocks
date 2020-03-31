package com.spring.boot.rocks.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.rocks.model.AppUser;

@Service
public class AppUserJDBCServiceImpl implements AppUserJDBCService {
 
 @Autowired
 private AppUserDaoImpl appuserDao;

 @Override
 public List<AppUser> getAllAppUsers() {
  return appuserDao.getAllAppUsers();
 }
}
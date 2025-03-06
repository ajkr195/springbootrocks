package com.spring.boot.rocks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.boot.rocks.model.AppUser;

@Service
public interface AppUserService {
    void saveUser(AppUser appuser);

    AppUser findByUseremail(String email);
    
    AppUser findByUseremailIgnoreCase(String email);

    List<AppUser> findAllUsers();
    
    List<AppUser> findAllActiveUsers();
    
    List<AppUser> findAllInActiveUsers();

	AppUser updateUser(AppUser user);
}

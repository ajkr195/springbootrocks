package com.spring.boot.rocks.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.rocks.model.AppRole;
import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppUserRepository;

@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AppUserRepository appUserJPARepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserJPARepository.findByUseremailIgnoreCase(username);
		if (user == null) {
			System.out.println("Login Error....!!!!\nUsername not found...");
			throw new UsernameNotFoundException("User not found.");

		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (AppRole role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUseremail(), user.getUserpassword(),
				grantedAuthorities);
	}
}

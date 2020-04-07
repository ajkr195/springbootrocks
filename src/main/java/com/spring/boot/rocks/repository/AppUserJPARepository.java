package com.spring.boot.rocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.model.AppUser;

@Repository
public interface AppUserJPARepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);
	
	@Query("SELECT u FROM AppUser u WHERE u.username LIKE %:username%")
	  List<AppUser> findByUsernameLike(String username);
	
//	List<AppUser> findByUsernameIgnoreCase(String username);
	
	List<AppUser> findByUsernameIgnoreCaseContaining(String username);
	
}

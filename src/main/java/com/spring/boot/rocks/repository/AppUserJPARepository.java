package com.spring.boot.rocks.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.model.AppUser;

@Repository
public interface AppUserJPARepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String username);
	
	@Query("SELECT u FROM AppUser u WHERE u.username LIKE %:username%")
	  List<AppUser> findByUsernameLike(String username);
	
//	List<AppUser> findByUsernameIgnoreCase(String username);
	
	List<AppUser> findByUsernameIgnoreCaseContaining(String username);
	
//	@Query("select user from AppUser u where datediff(curdate(),curdate(),u.userdatecreated)>=180")
	
	// 3 minutes
//	SELECT m FROM Mail m WHERE m.sentAt < sysdate - 3/(24*60)
//	@Query("SELECT u FROM AppUser u WHERE u.userdatecreated  < sysdate - 3/(24*60)")
	
//	@Query("select u FROM AppUser u WHERE u.userdatecreated <= :userdatecreated")
//	List<AppUser> findByUserdatecreated(Date userdatecreated);
	
	@Query("SELECT u FROM AppUser u WHERE u.userdatecreated < ?1")
	List<AppUser> findByUserdatecreated (Date userdatecreated);
	
}

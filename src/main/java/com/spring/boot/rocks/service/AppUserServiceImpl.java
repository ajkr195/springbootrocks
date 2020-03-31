package com.spring.boot.rocks.service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppUserJPARepository;

@Service
@Transactional(timeout = 5)
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	private AppUserJPARepository appUserJPARepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(AppUser user) {
		user.setUsername(user.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(user.getRoles());
		
		user.setUseremail(user.getUseremail());
		user.setUserfirstname(user.getUserfirstname());
		user.setUserlastname(user.getUserlastname());
		user.setUseraddress(user.getUseraddress());
		System.out.println("\n%%%%%%%%%%%      Adding New User.... " + user.getUsername() + "     %%%%%%%%%%%%%\n");
		appUserJPARepository.save(user);
	}

	@Override
	public AppUser findByUsername(String username) {
		return appUserJPARepository.findByUsername(username);
	}

	@Override
	public AppUser findByUserId(long userid) {
		AppUser obj = appUserJPARepository.findById(userid).get();
		return obj;
	}

	@Override
	@XmlElement(name = "employee")
	public List<AppUser> findAllUsers() {
		List<AppUser> list = new ArrayList<>();
		appUserJPARepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
	public void updateUser(AppUser user) {
		AppUser entity = appUserJPARepository.findById(user.getId()).orElse(null);
		if (entity != null) {
			System.out.println("\n%%%%%%%%%%%      Updating User.... " + user.getUsername() + "     %%%%%%%%%%%%%\n");

			entity.setUsername(user.getUsername());
			entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			entity.setPasswordConfirm(bCryptPasswordEncoder.encode(entity.getPassword()));
			entity.setUseremail(user.getUseremail());
			entity.setUserfirstname(user.getUserfirstname());
			entity.setUserlastname(user.getUserlastname());
			entity.setUseraddress(user.getUseraddress());
			entity.setRoles(user.getRoles());
			

		}
		appUserJPARepository.save(entity);
	}

	@Override
	public void deleteUserByUsername(String username) {
		System.out.println("\n%%%%%%%%%%%      Deleting User.... " + username + "     %%%%%%%%%%%%%\n");
		appUserJPARepository.delete(findByUsername(username));

	}
	
	@Override
	public List<Map<String, Object>> jasperhtmlreport() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (AppUser user : appUserJPARepository.findAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", user.getId());
			item.put("username", user.getUsername());
			item.put("useremail", user.getUseremail());
			item.put("userfirstname", user.getUserfirstname());
			item.put("userlastname", user.getUserlastname());
			item.put("useraddress", user.getUseraddress());
			result.add(item);
		}
		return result;
	}

	

}

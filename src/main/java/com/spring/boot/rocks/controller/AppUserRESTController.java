package com.spring.boot.rocks.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppUserJPARepository;

@RestController
@RequestMapping(path = "/api")
public class AppUserRESTController {
	@Autowired
	private AppUserJPARepository appUserJPARepository;

	@GetMapping(path = "/listappusers", produces = "application/json")
	public List<AppUser> getEmployees() {
		return appUserJPARepository.findAll();
	}
	
	@GetMapping("/findappuser/{id}")
	 public Optional<AppUser> getUser(@PathVariable long id) {
	  return appUserJPARepository.findById(id);
	 }
}

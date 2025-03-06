package com.spring.boot.rocks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.rocks.exception.ResourceNotFoundException;
import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppRoleRepository;
import com.spring.boot.rocks.repository.AppUserRepository;
import com.spring.boot.rocks.service.AppUserService;
import com.spring.boot.rocks.validation.AppUserAddValidator;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserRESTController {

	@Autowired
	AppRoleRepository appRoleRepository;

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	private AppUserAddValidator appUserNewValidator;

	private AppUserService userService;

	public UserRESTController(AppUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/listAllActiveUsers")
	List<AppUser> activeUsers() {
		return appUserRepository.findByUserenabledTrue();
	}

	@GetMapping("/listAllInActiveUsers")
	List<AppUser> inActiveUsers() {
		return appUserRepository.findByUserenabledFalse();
	}

	@GetMapping(path = "/appusers")
	public List<AppUser> getAppUsers() {
		return appUserRepository.findAll();
	}

	@GetMapping("/findappuser/{id}")
	public Optional<AppUser> getUser(@PathVariable long id) {
		return appUserRepository.findById(id);
	}

	@PostMapping("/appUser")
	public AppUser addAppUser(@Valid @RequestBody AppUser appUser) {
		log.info("Creating New User...");
		return appUserRepository.save(appUser);
	}

	@PutMapping("/appUser")
	public AppUser updateAppUser(@Valid @RequestBody AppUser appUser) {
		log.info("Updating User with ID: {}", appUser.getId());
		return appUserRepository.save(appUser);
	}

	@PostMapping(value = "/activateUser/{id}")
	public AppUser activateUser(@PathVariable Long id) {
		AppUser appUser = appUserRepository.findById(id).get();
		log.info("Activating User :: " + id);
		appUserRepository.setAppUserAsActiveById(id);
		return appUser;
	}
	
	@PostMapping(value = "/deActivateUser/{id}")
	public AppUser deActivateUser(@PathVariable Long id) {
		AppUser appUser = appUserRepository.findById(id).get();
		log.info("Activating User with Id - {}", id);
		appUserRepository.setAppUserAsInActiveById(id);
		return appUser;
	}

	@DeleteMapping(value = "/deleteUser/{id}")
	public AppUser deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
		AppUser appUser = appUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("AppUser not found for this id :: " + id));
		log.info("Deleting User with Id - {} ", id);
		appUserRepository.deleteById(id);
		return appUser;
	}

	@DeleteMapping(value = "/deleteAppUser/{id}")
	public AppUser deleteAppUser(@PathVariable Long id) {
		AppUser appUser = appUserRepository.findById(id).get();
		appUserRepository.deleteById(id);
		return appUser;
	}

	@PutMapping("/updateTheUser/{id}")
	public ResponseEntity<AppUser> updateTheAppUser(@Valid @RequestBody AppUser appUser, @PathVariable Long id)
			throws ResourceNotFoundException {
		AppUser theUser = appUserRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("AppUser not found for this id :: " + id));
		final AppUser updatedAppUser = appUserRepository.save(theUser);
		return ResponseEntity.ok(updatedAppUser);
	}

	@DeleteMapping("/deleteTheUser/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long appUserId)
			throws ResourceNotFoundException {
		AppUser employee = appUserRepository.findById(appUserId)
				.orElseThrow(() -> new ResourceNotFoundException("AppUser not found for this id :: " + appUserId));

		appUserRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/delteAppUserByTheId/{id}")
	 @ResponseBody
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			appUserRepository.deleteById(id);
			log.info("User with id {} got Delete successfully", id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

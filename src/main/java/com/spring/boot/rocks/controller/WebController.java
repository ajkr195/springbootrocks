package com.spring.boot.rocks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boot.rocks.model.AppDepartment;
import com.spring.boot.rocks.model.AppRole;
import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppDepartmentRepository;
import com.spring.boot.rocks.repository.AppRoleRepository;
import com.spring.boot.rocks.repository.AppUserRepository;
import com.spring.boot.rocks.service.AppUserService;
import com.spring.boot.rocks.validation.AppUserAddValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebController {
	
	@Autowired
	AppRoleRepository appRoleRepository;

	@Autowired
	AppDepartmentRepository appDepartmentRepository;
	
	private AppUserService userService;

	public WebController(AppUserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	AppUserAddValidator appUserNewValidator;
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@ModelAttribute("roles")
	public List<AppRole> initializeRoles() {
		return (List<AppRole>) appRoleRepository.findAll();
	}

	@ModelAttribute("departments")
	public List<AppDepartment> initializeDepartments() {
		return (List<AppDepartment>) appDepartmentRepository.findAll();
	}
	

	@GetMapping({"/", "/index", "/home"})
	String homePage(Model model) {
		model.addAttribute("pagename", "index");
		return "index";
	}
	
	@GetMapping({"/crm"})
	String crmPage(Model model) {
		model.addAttribute("pagename", "crm");
		return "index_crm";
	}
	
	@GetMapping({"/error404"})
	String error404Page(Model model) {
		model.addAttribute("pagename", "error404");
		return "error-404";
	}
	
	@GetMapping({"/errormaint"})
	String errormaintPage(Model model) {
		model.addAttribute("pagename", "errormaint");
		return "error-maintenance";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout, String regnsuccess) {

		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("logout", "You have been logged out successfully.");

		if (regnsuccess != null)
			model.addAttribute("regnsuccess", "Registration was successful. You can login now.");

		return "auth-login-basic";
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);

			SecurityContextHolder.getContext().setAuthentication(null);
			// session.invalidate();
		}
		return "redirect:login?logout";
	}

	


}

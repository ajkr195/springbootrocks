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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserWebController {

	@Autowired
	AppRoleRepository appRoleRepository;

	@Autowired
	AppDepartmentRepository appDepartmentRepository;

	private AppUserService userService;

	public UserWebController(AppUserService userService) {
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

	@GetMapping("/listuser")
	public String listUser(Model model) {
		try {
			model.addAttribute("pagename", "listuser");
			model.addAttribute("users", appUserRepository.findAll());
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}

		return "user_list";
	}

	@GetMapping("/listusers")
	public String getAll(Model model, @RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id,asc") String[] sort) {
		try {
			List<AppUser> tutorials = new ArrayList<AppUser>();

			String sortField = sort[0];
			String sortDirection = sort[1];

			Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
			Order order = new Order(direction, sortField);

			Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

			Page<AppUser> pageTuts;
			if (keyword == null) {
				pageTuts = appUserRepository.findAll(pageable);
			} else {
				pageTuts = appUserRepository.findByUseremailContainingIgnoreCase(keyword, pageable);
				model.addAttribute("keyword", keyword);
			}

			tutorials = pageTuts.getContent();
			model.addAttribute("users", tutorials);
			model.addAttribute("currentPage", pageTuts.getNumber() + 1);
			model.addAttribute("totalItems", pageTuts.getTotalElements());
			model.addAttribute("totalPages", pageTuts.getTotalPages());
			model.addAttribute("pageSize", size);
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDirection", sortDirection);
			model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
			model.addAttribute("pagename", "listusers");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}

		return "user_list";
	}

	@GetMapping({ "/adminuseredit/{id}" })
	public String adminusereditRegistrationsd(Model model, @PathVariable(required = false, name = "id") Long id) {
		String editinguser = "editinguser";
		model.addAttribute("editinguser", editinguser);
		Optional<AppUser> appuser = appUserRepository.findById(id);
		model.addAttribute("userEmailid", appuser.get().getUseremail());
		model.addAttribute("user", appUserRepository.findById(id));
		return "auth-register-basic";
	}

	@PostMapping("/adminuseredit")
	public String adminusereditRegistration(@Valid @ModelAttribute("user") AppUser user, BindingResult bindingResult,
			HttpServletRequest request, Model model) {

		// log.info("User ID is :: " + user.getId());

		String editinguser = "creatinguser";
		model.addAttribute("editinguser", editinguser);
		model.addAttribute("user", user);
		// appUserEditValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "auth-register-basic";
		}
		userService.updateUser(user);
		return "redirect:/listusers";
	}
	
	@GetMapping("/signup")
	public String userRegistrationsd(Model model) {
		String creatinguser = "creatinguser";
		model.addAttribute("creatinguser", creatinguser);
		model.addAttribute("user", new AppUser());
		return "auth-register-basic";
	}

	@PostMapping("/signup")
	public String userRegistration(@Valid @ModelAttribute("user") AppUser user, BindingResult bindingResult,
			HttpServletRequest request, Model model) {

		model.addAttribute("user", user);
		String creatinguser = "creatinguser";
		model.addAttribute("creatinguser", creatinguser);
		appUserNewValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "auth-register-basic";
		}
		userService.saveUser(user);
		return "redirect:/login?regnsuccess";
	}
	
	@GetMapping({"/accountsettings"})
	String accountsettingsPage(Model model) {
		model.addAttribute("pagename", "accountsettings");
		return "account-settings-account";
	}
	
	@GetMapping({"/accountconnections"})
	String accountnotificationsPage(Model model) {
		model.addAttribute("pagename", "accountconnections");
		return "account-settings-connections";
	}
	
	@GetMapping({"/accountnotifications"})
	String accountconnectionsPage(Model model) {
		model.addAttribute("pagename", "accountnotifications");
		return "account-settings-notifications";
	}
	
	@GetMapping({"/forgotpassword"})
	String forgotpasswordPage(Model model) {
		model.addAttribute("pagename", "forgotpassword");
		return "auth-forgot-password-basic";
	}

}

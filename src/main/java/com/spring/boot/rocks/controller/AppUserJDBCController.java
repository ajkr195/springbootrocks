package com.spring.boot.rocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.rocks.jdbc.AppUserJDBCServiceImpl;
import com.spring.boot.rocks.model.AppUser;

@Controller
@RequestMapping("/jdbc")
public class AppUserJDBCController {

	@Autowired
	private AppUserJDBCServiceImpl appUserJDBCServiceImpl;

	@RequestMapping(value = { "/userlist3" }, method = RequestMethod.GET)
	public ModelAndView getAllAppUsers() {
		ModelAndView model = new ModelAndView();
		List<AppUser> list = appUserJDBCServiceImpl.getAllAppUsers();

		model.addObject("users", list);
		model.setViewName("userlist3");
		return model;
	}
}

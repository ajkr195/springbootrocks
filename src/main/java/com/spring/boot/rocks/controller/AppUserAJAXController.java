package com.spring.boot.rocks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppUserAJAXController {

	@RequestMapping(value = { "userlist4" }, method = RequestMethod.GET)
	public String userajaxlist(Model model) {
		return "userlist4";
	}
}

package com.spring.boot.rocks.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

	@ModelAttribute("applicationName")
	public String getapplicationName() {
		return "SBOOT";
	}

	
}

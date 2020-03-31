package com.spring.boot.rocks.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spring.boot.rocks.model.AppRole;
import com.spring.boot.rocks.service.AppRoleService;

@Component
public class AppRoleConverter implements Converter<Object, AppRole> {

	static final Logger logger = LoggerFactory.getLogger(AppRoleConverter.class);

	@Autowired
	AppRoleService roleService;

	@Override
	public AppRole convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		AppRole role = roleService.findByid(id);
		logger.info("Profile : {}", role);
		return role;
	}
 
}
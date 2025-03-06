package com.spring.boot.rocks.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.service.AppUserService;



@Component
public class AppUserAddValidator implements Validator {
	
	@Autowired 
	AppUserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return AppUser.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		AppUser user = (AppUser) o;
		
		if (userService.findByUseremailIgnoreCase(user.getUseremail().trim()) != null) {
			errors.rejectValue("useremail", "DuplicateEmail");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userfirstname", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userlastname", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "useremail", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userpassword", "NotEmpty");
		

		if (!user.getPasswordConfirm().equals(user.getUserpassword())) {
			errors.rejectValue("passwordConfirm", "PasswordConfirmMismatch");
		}
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "NotEmpty");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "not.empty.multi.roles");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departments", "not.empty.multi.departments");

		
	}
}

package com.epoch.multidice.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.epoch.multidice.models.User;
import com.epoch.multidice.services.UserService;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserService users;
	
	public UserValidator() {}


	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		if (!user.getPassConfirm().equals(user.getPassword())) {
			errors.rejectValue("passConfirm", "Match");
		}
		
		if (users.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "NotUnique");
		}
		
	}
}
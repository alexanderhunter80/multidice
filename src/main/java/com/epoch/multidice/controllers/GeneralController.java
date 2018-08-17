package com.epoch.multidice.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.epoch.multidice.models.User;
import com.epoch.multidice.services.UserService;

@Controller
public class GeneralController {
	
	@Autowired
	private UserService users;
	
	public GeneralController() {};
	
	private User currentUser(Principal p) {
		return users.findByUsername(p.getName());
	}
	
	/*
	 * request mapping time
	 */
	
	@GetMapping("/")
	public String home(Principal principal, Model model) {
		return "home.jsp";
	}

}

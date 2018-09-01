package com.epoch.multidice.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.epoch.multidice.models.RollEvent;
import com.epoch.multidice.models.User;
import com.epoch.multidice.services.DiceService;
import com.epoch.multidice.services.UserService;

@Controller
public class GeneralController {
	
	@Autowired
	private UserService users;
	@Autowired
	private DiceService dice;
	
	public GeneralController() {};
	
	private User currentUser(Principal p) {
		return users.findByUsername(p.getName());
	}
	
	/*
	 * request mapping time
	 */
	
	@GetMapping("/")
	public String home(Principal principal, Model model, @ModelAttribute("rollEvent") RollEvent rollEvent) {
		
		User you;
		if(principal!=null) {
			you = users.findByUsername(principal.getName());
		} else {
			you = null;
		}
		model.addAttribute("user", you);
		
		return "home.jsp";
	}
	
	@GetMapping("/login")
	public String showLoginPage(Principal principal, Model model) {
		
		if(principal != null) {
			return "redirect:/";
		}
		
		return "login.jsp";
	}
	
	@GetMapping("/register")
	public String showRegistrationPage(Principal principal, Model model, @ModelAttribute("user") User user) {
		
		if(principal != null) {
			return "redirect:/";
		}
		
		return "register.jsp";
	}
	
	@PostMapping("/register") 
	public String doRegistration(Principal principal, Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
		
		if(principal != null) {
			return "redirect:/";
		}
		
		if(result.hasErrors()) {
			return "register.jsp";
		}
		
		// do actual registration with database and login user
		
		return "redirect:/";
	}
	
	@PostMapping("/roll")
	public String roll(Principal principal, Model model, @Valid @ModelAttribute("rollEvent") RollEvent rollEvent, BindingResult result) {
		
		if(result.hasErrors()) {
			return "home.jsp";
		}
		
		User you;
		if(principal!=null) {
			you = users.findByUsername(principal.getName());
		} else {
			you = null;
		}
		model.addAttribute("user", you);

		System.out.println(rollEvent.getInputString());
		// roll dice!
		RollEvent thisRoll = dice.createRollEvent(rollEvent);
		return "redirect:/result/"+thisRoll.getId().toString();  // 0 is placeholder, fix this once database is active
	}
	
	@GetMapping("/result/{id}")
	public String showOneResult(Principal principal, Model model, @ModelAttribute("rollEvent") RollEvent rollEvent, @PathVariable("id") Long id) {
		
		User you;
		if(principal!=null) {
			you = users.findByUsername(principal.getName());
		} else {
			you = null;
		}
		model.addAttribute("user", you);
		
		// grab info from one result
		// add info to model for display
		model.addAttribute("showEvent", dice.getEventById(id));
		
		return "result.jsp";
	}

}

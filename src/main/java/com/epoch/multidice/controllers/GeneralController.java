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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epoch.multidice.models.Role;
import com.epoch.multidice.models.RollEvent;
import com.epoch.multidice.models.User;
import com.epoch.multidice.services.DiceService;
import com.epoch.multidice.services.UserService;
import com.epoch.multidice.validators.UserValidator;

@Controller
public class GeneralController {
	
	@Autowired
	private UserService users;
	@Autowired
	private UserValidator uv;
	@Autowired
	private DiceService dice;
	
	
	public GeneralController() {};
	
	private User currentUser(Principal p) {
		User you;
		if(p!=null) {
			you = users.findByEmail(p.getName());
		} else {
			you = null;
		}
		return you;
	}
	
	/*
	 * request mapping time
	 */
	
	@GetMapping("/")
	public String home(Principal principal, Model model, @ModelAttribute("rollEvent") RollEvent rollEvent) {
		
		User you = currentUser(principal);
		model.addAttribute("user", you);
		
		System.out.print("Debugging Principal: ");
		try {
			System.out.println(principal.toString());
			for(Role r : you.getRoles()) {
				System.out.println("Has role: "+r.getName());
			}
		} catch (NullPointerException e) {
			System.out.println(principal);
		}
		
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
	public String doRegistration(Principal principal, Model model, @Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes flash) {
		
		if(principal != null) {
			return "redirect:/";
		}
		
		uv.validate(user, result);
		if(result.hasErrors()) {
			return "register.jsp";
		}
		
		// do actual registration with database and login user
		users.createUserWithUserRole(user);
		flash.addFlashAttribute("flashMessage","Thank you for registering, please login to complete the process.");
		
		return "redirect:/login";
	}
	
	@PostMapping("/roll")
	public String roll(Principal principal, Model model, @Valid @ModelAttribute("rollEvent") RollEvent rollEvent, BindingResult result) {
		
		if(result.hasErrors()) {
			return "home.jsp";
		}
		
		User you = currentUser(principal);
		model.addAttribute("user", you);

		System.out.println(rollEvent.getInputString());
		// roll dice!
		rollEvent.setUser(you);
		RollEvent thisRoll = dice.createRollEvent(rollEvent);
		return "redirect:/result/"+thisRoll.getId().toString();
	}
	
	@GetMapping("/result/{id}")
	public String showOneResult(Principal principal, Model model, @ModelAttribute("rollEvent") RollEvent rollEvent, @PathVariable("id") Long id) {
		
		User you = currentUser(principal);
		model.addAttribute("user", you);
		
		// grab info from one result
		// add info to model for display
		model.addAttribute("showEvent", dice.getEventById(id));
		
		return "result.jsp";
	}
	
	@GetMapping("/admin")
	public String adminConsole(Principal principal, Model model) {
		User you = currentUser(principal);
		model.addAttribute("user", you);
		
		return "admin.jsp";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied(Principal principal, Model model) {
		User you = currentUser(principal);
		model.addAttribute("user", you);
		
		return "accessDenied.jsp";
	}
	
	@Controller
	@RequestMapping("/debug")
	public class DebugSubController {
		
		@GetMapping("/makeMeUser")
		public String makeUser(Principal principal, Model model) {
			User you = currentUser(principal);
			try {
				users.makeUser(you);
			} catch (NullPointerException e) {
				System.out.println("makeUser failed!");
			}
			return "redirect:/";
		}
		
		@GetMapping("/makeMeAdmin")
		public String makeAdmin(Principal principal, Model model) {
			User you = currentUser(principal);
			try {
				users.makeAdmin(you);
			} catch (NullPointerException e) {
				System.out.println("makeAdmin failed!");
			}
			return "redirect:/";
		}
		
		@GetMapping("/makeMeSuper")
		public String makeSuper(Principal principal, Model model) {
			User you = currentUser(principal);
			try {
				users.makeSuper(you);
			} catch (NullPointerException e) {
				System.out.println("makeSuper failed!");
			}
			return "redirect:/";
		}
		
	}

}

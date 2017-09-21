package com.ngm01.admindash.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ngm01.admindash.models.User;
import com.ngm01.admindash.services.UserService;
import com.ngm01.admindash.validator.UserValidator;

@Controller
public class UserController {
	
	private UserService userService;
	private UserValidator userValidator;
	
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		userValidator.validate(user, result);
		
		//Ask Will or Tony:
		//isn't this bad practice to render a page in Post?
		//If so, is there any way to save (ie in the model) the error results produced
		//by the UserValidator?
		if(result.hasErrors()) {
			return "loginPage.jsp";
		}
		if(userService.countAdmins() < 1) {
			userService.saveWithAdminRole(user);
		}
		else {
			userService.saveWithUserRole(user);
		}
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String login(@Valid @ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
		System.out.println(userService.countAdmins());
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid credentials. Please try again.");
		}
		if (logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		return "loginPage.jsp";
	}
	
	@RequestMapping(value = {"/", "dashboard"})
	public String home(Principal principal, Model model) {
		String username = principal.getName();
		User currentUser = userService.findByUsername(username);
		currentUser.setLastSignIn(new Date());
		userService.updateUser(currentUser);
		model.addAttribute("currentUser", currentUser);
		return "dashboard.jsp";
	}
	
	@RequestMapping("/admin")
	public String adminPage(Principal principal, Model model) {
		String username = principal.getName();
		List<User> users = userService.readAll();
		model.addAttribute("currentUser", userService.findByUsername(username));
		model.addAttribute("users", users);
		return "adminPage.jsp";
	}
	
	@PostMapping("/delete")
	public String deleteUser(@RequestParam(value="user") Long id){
		userService.deleteUser(id);
		return "redirect:/admin";
	}
	
	@PostMapping("/makeAdmin")
	public String raisePrivelege(@RequestParam(value="user") Long id) {
		User user = userService.readOne(id);
		userService.saveWithAdminRole(user);
		return "redirect:/admin";
	}

}

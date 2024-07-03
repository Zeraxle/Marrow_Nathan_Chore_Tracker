package com.nathanmarrow.chore_tracker.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nathanmarrow.chore_tracker.models.LoginUser;
import com.nathanmarrow.chore_tracker.models.User;
import com.nathanmarrow.chore_tracker.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired 
	private UserService service;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model) {
		
		User takenEmail = service.get(user.getEmail());
		if (takenEmail != null) {
			result.rejectValue("email", "unique", "Email is already taken");
		}
		 
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			    result.rejectValue("confirmPassword", "matches", "Confirm Password must match Password!");
			}
		 
	     if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		 
		String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashPassword);
		Long userId = service.create(user).getId();
		session.setAttribute("loggedInUser", userId);
		return "redirect:/jobs";
	}
	
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser loginUser, BindingResult result, Model model) {
		
		User potentialUser = service.get(loginUser.getEmail());
		if (potentialUser == null) {
			result.rejectValue("email", "invalid user", "Invalid login");
		}
		
		else {
			if (!BCrypt.checkpw(loginUser.getPassword(), potentialUser.getPassword())) {
				result.rejectValue("email", "invalid user", "Invalid login");
			}
		}
		
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		session.setAttribute("loggedInUser", potentialUser.getId());
		return "redirect:/jobs";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

package com.nathanmarrow.chore_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.nathanmarrow.chore_tracker.models.LoginUser;
import com.nathanmarrow.chore_tracker.models.User;

import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/")
		public String index(@ModelAttribute("newUser") User user, Model model) {
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
		
	}

}

package com.nathanmarrow.chore_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nathanmarrow.chore_tracker.models.Worker;
import com.nathanmarrow.chore_tracker.services.WorkerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class WorkerController {

	@Autowired
	WorkerService service;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/workers/new")
	public String newWorker(@ModelAttribute("worker") Worker worker, RedirectAttributes redirect, Model model) {
		if (session.getAttribute("loggedInUser") == null) {
			redirect.addFlashAttribute("notLoggedIn", "You must be logged in to view that page");
			return "redirect:/";
		}
		model.addAttribute("userId", (Long)session.getAttribute("loggedInUser"));
		return "createWorker.jsp";
	}
	
	@PostMapping("/workers/create")
	public String createWorker(@Valid @ModelAttribute("newWorker") Worker worker, BindingResult result) {
		if (result.hasErrors()) {
			return "createWorker.jsp";
		}
		service.create(worker);
		return "redirect:/jobs";
	}
	
}

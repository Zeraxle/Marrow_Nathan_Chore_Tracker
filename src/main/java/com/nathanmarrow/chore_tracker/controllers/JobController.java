package com.nathanmarrow.chore_tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nathanmarrow.chore_tracker.models.Job;
import com.nathanmarrow.chore_tracker.models.User;
import com.nathanmarrow.chore_tracker.models.Worker;
import com.nathanmarrow.chore_tracker.services.JobService;
import com.nathanmarrow.chore_tracker.services.UserService;
import com.nathanmarrow.chore_tracker.services.WorkerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class JobController {
	
	@Autowired
	public JobService service;
	
	@Autowired 
	public UserService uService;
	
	@Autowired
	public WorkerService wService;
	
	@Autowired 
	public HttpSession session;
	
	@GetMapping("/jobs")
	public String homePage(@ModelAttribute("updatedJob") Job updatedJob, RedirectAttributes redirect, Model model) {
		if (session.getAttribute("loggedInUser") == null) {
			redirect.addFlashAttribute("notLoggedIn", "You must be logged in to view that page");
			return "redirect:/";
		}
		List<Job> allJobs = service.getAll();
		User user = uService.get((Long) session.getAttribute("loggedInUser"));
		
		if(user.getLicensedWorker() != null) {
			Worker worker = user.getLicensedWorker();
			List<Job> myJobs = worker.getTakenJobs();
			model.addAttribute("allMyJobs", myJobs);
		}
		
		model.addAttribute("allJobs", allJobs);
		model.addAttribute("user", user);
		return "dashboard.jsp";
	}
	
	@GetMapping("/jobs/new")
	public String newJob(@ModelAttribute("newJob") Job job, Model model, RedirectAttributes redirect) {
		if (session.getAttribute("loggedInUser") == null) {
			redirect.addFlashAttribute("notLoggedIn", "You must be logged in to view that page.");
			return "redirect:/";
		}
		model.addAttribute("userId", (Long)session.getAttribute("loggedInUser"));
		return "addJob.jsp";
	}
	
	@PostMapping("/jobs/create")
	public String createJob(@Valid @ModelAttribute("newJob") Job job, BindingResult result) {
		
		if (result.hasErrors()) {
			return "addJob.jsp";
		}
		service.create(job);
		return "redirect:/jobs";
	}

	@GetMapping("jobs/show/{id}")
	public String showJob(@ModelAttribute("updatedJob") Job updatedJob, @PathVariable("id") Long id, 
			Model model, RedirectAttributes redirect) {
		if (session.getAttribute("loggedInUser") == null) {
			redirect.addFlashAttribute("notLoggedIn", "You must be logged in to view that page");
			return "redirect:/";
		}
		User user = uService.get((Long) session.getAttribute("loggedInUser"));
		Job job = service.get(id);
		
		model.addAttribute("job", job);
		model.addAttribute("user", user);
		return "jobDetails.jsp";
	}

	@GetMapping("/jobs/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
		if (session.getAttribute("loggedInUser") == null) {
			redirect.addFlashAttribute("notLoggedIn", "You must be logged in to view that page");
			return "redirect:/";
		}
		User user = uService.get((Long) session.getAttribute("loggedInUser"));
		Job job = service.get(id);
		if(user != job.getJobOwner()) {
			return "redirect:/logout";
		}
		model.addAttribute("user", user);
		model.addAttribute("job", job);
		return "editJob.jsp";
	}
	
	@PutMapping("/jobs/update/{id}")
	public String update(@Valid @ModelAttribute("updatedJob") Job updatedJob, BindingResult result, @PathVariable("id") Long id, 
			Model model, RedirectAttributes redirect) {
		
		User user = uService.get((Long) session.getAttribute("loggedInUser"));
		
		if(user.getLicensedWorker() == null) {
			redirect.addFlashAttribute("notLicensed", "You must be licensed to take a job!");
			return "redirect:/workers/new"; 
		}
		
		if(result.hasErrors()) {
			return "editJob.jsp";
		}
			updatedJob.setId(id);
		
			service.update(updatedJob);
			return "redirect:/jobs";
		
	}
	
	@PutMapping("/jobs/add/{id}")
	public String takeJob(@Valid @ModelAttribute("updatedJob") Job updatedJob, BindingResult result, @PathVariable("id") Long id, 
			Model model, RedirectAttributes redirect) {
		
		User user = uService.get((Long) session.getAttribute("loggedInUser"));
		
		if(user.getLicensedWorker() == null) {
			redirect.addFlashAttribute("notLicensed", "You must be licensed to take a job!");
			return "redirect:/workers/new"; 
		}
		if(user == updatedJob.getJobOwner()) {
			redirect.addFlashAttribute("jobOwnerWarning", "You can't take a job that you posted!");
			return "redirect:/jobs";
		}
		if(result.hasErrors()) {
			return "editJob.jsp";
		}
			updatedJob.setId(id);
		
			service.update(updatedJob);
			return "redirect:/jobs";
		
	}
	
	@DeleteMapping("/jobs/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirect) {
		if (session.getAttribute("loggedInUser") == null) {
			redirect.addFlashAttribute("notLoggedIn", "You must be logged in to view that page");
			return "redirect:/";
		}
		service.destroy(id);
		return "redirect:/jobs";
	}

}

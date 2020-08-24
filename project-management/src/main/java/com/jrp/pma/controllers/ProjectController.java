package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	ProjectService proService;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees = empService.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		proService.save(project);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
	
	@GetMapping
	public String displayProject(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projects", projects);
		
		// use a redirect to prevent duplicate submissions
		return "projects/list-projects";
	}
}

// employee controller
// html form
// new_employee (post to save)
// save method (save to database)


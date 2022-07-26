package com.redasp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redasp.pma.dto.ProjectTimeLine;
import com.redasp.pma.entities.Employee;
import com.redasp.pma.entities.Project;
import com.redasp.pma.services.EmployeeService;
import com.redasp.pma.services.ProjectService;


@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService proService;

	@Autowired
	EmployeeService empService;

	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projects", projects);
		return "projects/listsProject";

	}

	@GetMapping("/new")
	public String displayProjectForm(Model model) {

		Project aProject = new Project();
		List<Employee> employees = empService.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		return "projects/newProject";
	}

	@PostMapping("/save")
	public String createProject( Model model, @Valid Project project, Errors errors) {
		
		if(errors.hasErrors())
		{
			return "projects/newProject"; 
		}
		

		// should handle saving to database...
		proService.save(project);

		// use redirect to prevent duplicate submission
		return "redirect:/projects";
		//
	}
	
	
	@GetMapping("/update")
	public String updateProject(@RequestParam("id") long theId, Model model) {
		
		Project theProj = proService.findByProjectId(theId);

		model.addAttribute("project", theProj);

		return "projects/updateProject";
	}

	
	
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long theId, Model model) {

		Project theProj = proService.findByProjectId(theId);

		proService.delete(theProj);
		
		// use redirect to prevent duplicate submission
		return "redirect:/projects";

	}
	
	@GetMapping("/timeline")
	public String displayProjectTimeLine(Model model) throws JsonProcessingException
	{
		
		List<ProjectTimeLine> timelineData= proService.getTimeData();
		
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonTimeLine=objectMapper.writeValueAsString(timelineData);
		
		model.addAttribute("projectTimeList", jsonTimeLine);
		return "projects/project-timeline";
	}

}

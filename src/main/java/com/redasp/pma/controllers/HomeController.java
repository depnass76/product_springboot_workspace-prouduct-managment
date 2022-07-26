package com.redasp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redasp.pma.dao.IEmployeeRepository;
import com.redasp.pma.dao.IProjectRepository;
import com.redasp.pma.dto.EmployeeProject;
import com.redasp.pma.dto.EmployeeProjectCount;
import com.redasp.pma.dto.ProjectStage;
import com.redasp.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	IProjectRepository proRepo;
	
	@Autowired
	IEmployeeRepository empRepo;
	
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String,Object> map = new HashMap<>();
		
		
		model.addAttribute("version",ver);
		
		
		//Querying the database for the projects
		List<Project> projects =proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		
		List<ProjectStage> projectStageCount = proRepo.getProjectStatus();
		
		//Convert projectStageCount object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString =objectMapper.writeValueAsString(projectStageCount);
		//[[COMPLETED,2],[INPROGRESS,2],[NOTSTARTED,1]]
		model.addAttribute("projectsListStageCount", jsonString);
		
		
		
		List<EmployeeProjectCount> employeesCountProject =empRepo.employeeProjectsCount();
		//Convert projectStageCount object into a json structure for use in javascript
		ObjectMapper objectMapper1 = new ObjectMapper();
	    String jsonString1 =objectMapper1.writeValueAsString(employeesCountProject);
		//[[lastName,2],[lastName,2]
		model.addAttribute("employeesCount", jsonString1);
		
		
		
		
		
		//Querying the database for the employees
		List<EmployeeProject> employeesProjectCount =empRepo.employeeProjects();
		model.addAttribute("employeesListProjectCount", employeesProjectCount);
		
		
		return "main/home";
	}
	
	

}

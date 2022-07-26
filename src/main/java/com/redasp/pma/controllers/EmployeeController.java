package com.redasp.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.redasp.pma.entities.Employee;
import com.redasp.pma.services.EmployeeService;

@Controller
@RequestMapping(path="/employees")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@GetMapping
	public String displayEmployee(Model model) {
		List<Employee> employees = empService.getAll();
		model.addAttribute("employees", employees);
		return "employees/listsEmployee";

	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee aEmployee = new Employee();
		model.addAttribute("employee", aEmployee);
		return "employees/newEmployee";
	}

	
	
	@PostMapping("/save")
	public String createEmployee( Model model, @Valid Employee employee, Errors errors) {  
		
		if(errors.hasErrors())
		{
			return "employees/newEmployee"; 
		}		
     	// should handle saving to database...
		empService.save(employee);
		// use redirect to prevent duplicate submission
		return "redirect:/employees";

	}
	



	@GetMapping("/update")
	public String updateEmployee(@RequestParam("id") long theId, Model model) {
			
		
		Employee theEmp = empService.findByEmployeeId(theId);

		model.addAttribute("employee", theEmp);

		return "employees/updateEmployee";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {

		Employee theEmp = empService.findByEmployeeId(theId);

		empService.delete(theEmp);
		
		// use redirect to prevent duplicate submission
		return "redirect:/employees";

	}

}

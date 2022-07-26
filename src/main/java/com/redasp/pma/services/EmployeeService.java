package com.redasp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redasp.pma.dao.IEmployeeRepository;
import com.redasp.pma.dto.EmployeeProject;
import com.redasp.pma.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	IEmployeeRepository empRepo;

	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}

	public List<Employee> getAll() {
		return empRepo.findAll();
	}

	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}

	public Employee findByEmployeeId(long theId) {

		return empRepo.findByEmployeeId(theId);
	}

	public Employee findByEmail(String value) {
		return empRepo.findByEmail(value);
	}

	public void delete(Employee theEmp) {

		empRepo.delete(theEmp);

	}
}

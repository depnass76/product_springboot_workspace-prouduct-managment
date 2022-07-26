package com.redasp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redasp.pma.dto.EmployeeProject;
import com.redasp.pma.dto.EmployeeProjectCount;
import com.redasp.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel = "apiemployees", path = "apiemployees")
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	@Override
	public List<Employee> findAll();

	@Query(nativeQuery = true, value = "SELECT FIRST_NAME as FirstName, LAST_NAME as LastName ,Phone as Phone, COUNT(*) as NumberOfProject FROM EMPLOYEE E LEFT JOIN PROJECT_EMPLOYEE PE ON PE.EMPLOYEE_ID = E.EMPLOYEE_ID GROUP BY E.FIRST_NAME,E.LAST_NAME,E.Phone ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

	@Query(nativeQuery = true, value = "SELECT  LAST_NAME as label , COUNT(*) as value FROM EMPLOYEE E LEFT JOIN PROJECT_EMPLOYEE PE ON PE.EMPLOYEE_ID = E.EMPLOYEE_ID GROUP BY E.LAST_NAME")
	public List<EmployeeProjectCount> employeeProjectsCount();

	public Employee findByEmail(String value);

	public Employee findByEmployeeId(long theId);

}

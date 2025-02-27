package com.employee.crudapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.crudapi.model.Employee;
import com.employee.crudapi.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/employee")
	public String createEmployee(@RequestBody Employee employee)
	{
		employeeRepository.save(employee);
		return "Employee detailes saved";
	}
	
	@GetMapping("/allEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee()
	{
		List<Employee> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
	}
	
	@GetMapping("/getEmployeeById/{empId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId)
	{
		Optional<Employee> emp = employeeRepository.findById(empId);
		if(emp.isEmpty())
		{
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		else {
			
			return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
		}
	}
	
	@PutMapping("/updateEmployee/{empId}")
	public String updateEmployeeById(@PathVariable Long empId,@RequestBody Employee employee)
	{
		Optional<Employee> emp= employeeRepository.findById(empId);
		if(emp.isPresent())
		{
			Employee existEmp = emp.get();
			existEmp.setEmp_name(employee.getEmp_name());
			existEmp.setEmp_salary(employee.getEmp_salary());
			existEmp.setEmp_age(employee.getEmp_age());
			existEmp.setEmp_city(employee.getEmp_city());
			employeeRepository.save(existEmp);
			return "Employee details updated";
		}
		else
			return "employee id does not exist";
	}
	
	@DeleteMapping("/deleteEmployee/{empId}")
	public String deleteEmployeeById(@PathVariable long empId)
	{
		employeeRepository.deleteById(empId);
		return "Employee deleted successfully"; 
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAllEmployee()
	{
		employeeRepository.deleteAll();
		return "Delete All Employee";
	}
}

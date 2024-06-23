package com.employee.crudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.crudapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{



}

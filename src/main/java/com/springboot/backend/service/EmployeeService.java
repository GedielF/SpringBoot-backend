package com.springboot.backend.service;

import java.util.List;

import com.springboot.backend.model.Employee;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long Id);
	Employee updateEmployee(Employee employee,long id);
	void deleteEmployee(long id);
}

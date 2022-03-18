package com.springboot.backend.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.backend.model.Employee;
import com.springboot.backend.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	//create
	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	//get all
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll() ;
	}
	//get by id
	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee","Id",Id);
//		}
		return employeeRepository.findById(id).orElseThrow(()->
			new ResourceNotFoundException("employee","id",id));
	}
	//update
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//we need to check whether employee with given id is exist in DB or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Employee","Id",id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		//save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
	}
	//delete
	@Override
	public void deleteEmployee(long id) {
		
		//check whether a employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Employee","Id",id));
		employeeRepository.deleteById(id);
		
	}
}

package com.springboot.backend.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.backend.model.Employee;
import com.springboot.backend.repository.EmployeeRepository;
import com.springboot.backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}



	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}



	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		} else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		
		
	}



	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		// We need to check whether employee with the given ID exist in the database or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() 
				-> new ResourceNotFoundException ("Employee", "id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		// save existing employee to database
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
	}



	@Override
	public void deleteEmployee(long id) {
		
		// Check whether the employee exist in the id
		employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "id", id));
		
		employeeRepository.deleteById(id);
		
	}
	
	

	
}

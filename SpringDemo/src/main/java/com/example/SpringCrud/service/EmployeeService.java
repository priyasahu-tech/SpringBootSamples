package com.example.SpringCrud.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.SpringCrud.Exception.RecordNotFoundException;
import com.example.SpringCrud.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	public Employee getEmployeeById(Long id) throws RecordNotFoundException;
	public void  createEmployee(Employee employee) throws RecordNotFoundException;
	public void deleteById(Long id)throws RecordNotFoundException;
	public Employee updateEmployee(Employee newEmployee, Long id) ;
	
	

}

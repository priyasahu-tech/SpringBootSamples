package com.example.SpringCrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.SpringCrud.Exception.RecordNotFoundException;
import com.example.SpringCrud.model.Employee;
import com.example.SpringCrud.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository EmployeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		LOG.debug("Inside into Repository class..... ");
		List<Employee> emplist = EmployeeRepository.findAll();
		if (emplist.size() > 0) {
			return emplist;
		} else {
			return new ArrayList<Employee>();
		}
	}

	@Override
	public Employee getEmployeeById(Long id) throws RecordNotFoundException {
		LOG.debug("Inside into getEmployeeID method class..... ");
		Optional<Employee> employee = EmployeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	@Override
	public void deleteById(Long id) throws RecordNotFoundException {
		LOG.debug("Inside into deleteById method class..... ");
		Optional<Employee> empdelete = EmployeeRepository.findById(id);
		if (empdelete.isPresent()) {
			EmployeeRepository.deleteById(id);
		} else
			throw new RecordNotFoundException("No employee record exist for given id");
	}

	@Override
	public void createEmployee(Employee employee) throws RecordNotFoundException {
		Employee newEntity = new Employee();
		// Employee newEntity = employee.getId();
		newEntity.setFname(employee.getFname());
		newEntity.setLname(employee.getLname());
		newEntity.setSalary(employee.getSalary());
		newEntity.setDepartment(employee.getDepartment());
		 EmployeeRepository.save(newEntity).getId();
	}

	@Override
	public Employee updateEmployee(Employee newEmployee, Long id) 
	    {
	        Optional<Employee> employee = EmployeeRepository.findById(newEmployee.getId());
	         
	        if(employee.isPresent()) 
	        {
	            Employee newEntity = employee.get();
	            newEntity.setFname(newEmployee.getFname());
	            newEntity.setLname(newEmployee.getLname());
	            newEntity.setSalary(newEmployee.getSalary());
	            newEntity.setDepartment(newEmployee.getDepartment());
	 
	            newEntity = EmployeeRepository.save(newEntity);
	             
	            return newEntity;
	        }  else {
	        	newEmployee = EmployeeRepository.save(newEmployee);
	             
	            return newEmployee;
	        }
	    } 
	     



}

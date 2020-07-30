package com.example.SpringCrud.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.SpringCrud.Exception.RecordNotFoundException;
import com.example.SpringCrud.model.Employee;
import com.example.SpringCrud.repository.EmployeeRepository;
import com.example.SpringCrud.service.EmployeeService;

@RestController
public class EmployeeController<JsonPatch> {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeRepository repository;

	public EmployeeRepository getRepository() {
		return repository;
	}

	public void setRepository(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		LOG.debug("Inside into getAllEmployees() Methods ..Controller .....");
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		LOG.debug("Inside into getEmployeeById() .....Controller .....");
		Employee getid = employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(getid, HttpStatus.OK);

	}

	@DeleteMapping("/employees/{id}")
	public HttpStatus deleteById(@PathVariable Long id) throws RecordNotFoundException {
		employeeService.deleteById(id);
		return HttpStatus.ACCEPTED;
	}

	@PostMapping("/employees")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
		Employee emp = repository.save(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emp.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
         Employee updated = employeeService.updateEmployee(newEmployee, id);
         	return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);}
	
	/*
	 * @PatchMapping("/employees/{id}") 
	 * public @ResponseBody ResponseEntity<String> patch() {
	 *   Employee updated = employeeService.updateEmployee(newEmployee, id);
	 * return new ResponseEntity<String>("PATCH Response", HttpStatus.OK); }
	 */
	
	
}
package com.bhupinder.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bhupinder.dao.entity.Employee;
import com.bhupinder.dto.EmployeeDetail;
import com.bhupinder.service.EmployeeService;

/**
 * This is Rest end point for employee Service for Tax
 * @author Bhupinder
 *
 */
@RestController
public class EmployeeResource {

	@Autowired
	private EmployeeService service;

	@GetMapping("/employees")
	public List<EmployeeDetail> getEmployees() {
		return service.getAllEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public EmployeeDetail getEmployee(@PathVariable int id) {
		return service.getEmployeeById(id);
	}

	@PostMapping("/employees")
	public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) {
		employee = service.save(employee);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(employee.getEmployeeId())
											 .toUri();
		return ResponseEntity.created(uri).build();
	}
}

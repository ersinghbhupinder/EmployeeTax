package com.bhupinder.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhupinder.dao.EmployeeRepository;
import com.bhupinder.dao.entity.Employee;
import com.bhupinder.dto.EmployeeDetail;
import com.bhupinder.exception.EmployeeNotFoundException;
import com.bhupinder.util.TaxUtil;
/**
 * This class contains business logic.
 * @author Bhupinder
 *
 */
@Service
public class EmployeeService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * This method fetch information of all employee and provide tax details.
	 * @return List of EmployeeDetail
	 */
	public List<EmployeeDetail> getAllEmployees() {
		LOGGER.info("Start getAllEmployees()");
		List<Employee> employeeList = employeeRepository.findAll();
		List<EmployeeDetail> transformedList = employeeList.stream()
													.map(e -> transform(e))
													.collect(Collectors.toList());
		LOGGER.info("End getAllEmployees()");
		return transformedList;
	}

	/**
	 * This method take Employee as input and transform to EmployeeDetail and calculate the tax for the employee
	 * @param employee
	 * @return EmployeeDetail
	 */
	private EmployeeDetail transform(Employee employee) {
		// We can use builder pattern instead of constructor
		EmployeeDetail employeeDetail = new EmployeeDetail(employee.getEmployeeId(), 
														   employee.getEmployeeName(), 
														   employee.getSalary());
		employeeDetail.setTaxes(TaxUtil.calculateTaxes(employeeDetail.getSalary()));
		return employeeDetail;
	}

	
	/**
	 * This method takes input an Employee and make request to save to database.
	 * @param employee
	 * @return
	 */
	public Employee save(Employee employee) {
		employee = employeeRepository.save(employee);
		LOGGER.info("Successfully created entry {} for employee : {}", employee.getEmployeeId(), employee.getEmployeeName());
		return employee;
	}

	/**
	 * This method takes employee Id as input and fetch particular employee. 
	 * @param id
	 * @return
	 */
	public EmployeeDetail getEmployeeById(int id) {
		LOGGER.info("Start fetching employee information {}", id);
		Optional<Employee> findById = employeeRepository.findById(id);
		if(findById.isEmpty()) {
			throw new EmployeeNotFoundException(String.format("Employee id %s not found.", id));
		}
		 Employee employee = findById.get();
		 EmployeeDetail employeeDetail = transform(employee);
		 LOGGER.info("End fetching employee information {}, {}", id , employeeDetail.getEmployeeName());
		 return employeeDetail;
	}
}

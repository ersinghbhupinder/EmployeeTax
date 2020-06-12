package com.bhupinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhupinder.dao.entity.Employee;

/**
 * This class is for persisting data into database. We are using Spring JPA, all operaton are handled by Spring
 * @author Bhupinder
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

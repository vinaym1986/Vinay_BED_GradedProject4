package com.greatlearning.ems.repository;
import com.greatlearning.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee,Integer> {
    List<Employee> findByFirstName(String firstName);
}
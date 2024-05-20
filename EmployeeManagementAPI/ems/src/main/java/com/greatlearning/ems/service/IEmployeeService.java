package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    Employee  saveEmployee(Employee employee);

    boolean deleteEmployee(Integer id);

    List<Employee> getEmployeesByFirstName(String firstName);

    List<Employee> getAllEmployeesSortedAscending();
    
    List<Employee> getAllEmployeesSortedDescending();
    
}

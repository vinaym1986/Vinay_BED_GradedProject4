package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.repository.EmployeeRepository;
import com.greatlearning.ems.security.entity.Role;
import com.greatlearning.ems.security.entity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Employee> getEmployeesByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    @Override
    public List<Employee> getAllEmployeesSortedAscending() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }

    @Override
    public List<Employee> getAllEmployeesSortedDescending() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
    }


}

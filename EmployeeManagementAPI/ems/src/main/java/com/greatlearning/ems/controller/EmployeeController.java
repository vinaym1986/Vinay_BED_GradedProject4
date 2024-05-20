package com.greatlearning.ems.controller;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.models.EmployeeRequest;
import com.greatlearning.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());

        Employee createdEmployee = employeeService.createEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @GetMapping("list")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        var employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updatedEmployee) {
        Employee existingEmployee = employeeService.getEmployeeById(updatedEmployee.getId());

        if (existingEmployee != null) {
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setEmail(updatedEmployee.getEmail());

            Employee newEmployee = employeeService.saveEmployee(existingEmployee);

            return ResponseEntity.status(HttpStatus.OK).body(newEmployee);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Deleted employee id - " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with id " + id + " not found");
        }
    }

    @GetMapping("/search/{firstName}")
    public ResponseEntity<List<Employee>> getEmployeesByFirstName(@PathVariable String firstName) {
        List<Employee> employees = employeeService.getEmployeesByFirstName(firstName);
        if (!employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(employees);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Employee>> getAllEmployeesSorted(@RequestParam("order") String order) {
        List<Employee> employees;
        if ("asc".equalsIgnoreCase(order)) {
            employees = employeeService.getAllEmployeesSortedAscending();
        } else if ("desc".equalsIgnoreCase(order)) {
            employees = employeeService.getAllEmployeesSortedDescending();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }
}




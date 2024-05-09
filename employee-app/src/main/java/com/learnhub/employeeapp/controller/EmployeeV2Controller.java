package com.learnhub.employeeapp.controller;

import com.learnhub.employeeapp.model.Employee;
import com.learnhub.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v2/employees")
public class EmployeeV2Controller {
    @Autowired
    @Qualifier("employeeV2ServiceImpl")
    EmployeeService employeeService;
    @PostMapping
    public Employee save(@RequestBody Employee employee) { return employeeService.save(employee);
    }
    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @PathVariable("id") String employeeId
    ) {
        return employeeService.getEmployeeById(employeeId);
    }
    @DeleteMapping("/{id}")
    public String deleteEmployeeById(
            @PathVariable("id") String employeeId
    ) {
        return employeeService.deleteEmployeeById(employeeId);
    }
}

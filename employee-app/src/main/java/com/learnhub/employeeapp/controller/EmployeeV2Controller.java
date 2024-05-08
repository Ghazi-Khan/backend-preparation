package com.learnhub.employeeapp.controller;

import com.learnhub.employeeapp.model.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/employees")
public class EmployeeV2Controller {
//    This controller is just to demonstrate
//    the concept of api versioning
    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employee;
    }
}

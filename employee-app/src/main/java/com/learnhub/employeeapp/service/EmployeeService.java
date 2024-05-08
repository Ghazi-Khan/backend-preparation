package com.learnhub.employeeapp.service;

import com.learnhub.employeeapp.model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee save(Employee employee);

    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(String employeeId);
}

package com.learnhub.employeeapp.service;

import com.learnhub.employeeapp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService{
    @Override
    public Employee save(Employee employee) {
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        return null;
    }

    @Override
    public String deleteEmployeeById(String employeeId) {
        return null;
    }
}

package com.learnhub.employeeapp.service;

import com.learnhub.employeeapp.exception.EmployeeNotFoundException;
import com.learnhub.employeeapp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();
    @Override
    public Employee save(Employee employee) {

        Predicate<Employee> doesEmployeeIdExistsPredicate =
                e -> e.getEmployeeId() != null && !e.getEmployeeId().isEmpty();

        if (!doesEmployeeIdExistsPredicate.test(employee)) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElseThrow(
                        () -> new EmployeeNotFoundException(
                                "Employee not found with id: " + employeeId
                        )
                );
    }

    @Override
    public String deleteEmployeeById(String employeeId) {
        Employee employeeObj = employees
                .stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .get();
        employees.remove(employeeObj);
        return "Employee with id " + employeeId + " deleted successfully";
    }
}

package com.learnhub.employeeapp.service;

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

        Predicate<Employee> doesEmployeeExistsEmployeePredicate =
                e -> e.getEmployeeId() != null && !e.getEmployeeId().isEmpty();

        if (!doesEmployeeExistsEmployeePredicate.test(employee)) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        employees.add(employee);
        return employee;
    }
}

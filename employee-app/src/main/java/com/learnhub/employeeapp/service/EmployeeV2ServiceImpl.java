package com.learnhub.employeeapp.service;

import com.learnhub.employeeapp.entity.EmployeeEntity;
import com.learnhub.employeeapp.model.Employee;
import com.learnhub.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService{


    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Employee save(Employee employee) {
        Predicate<Employee> doesEmployeeIdExistsPredicate =
                e -> e.getEmployeeId() != null && !e.getEmployeeId().isEmpty();

        if (!doesEmployeeIdExistsPredicate.test(employee)) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
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

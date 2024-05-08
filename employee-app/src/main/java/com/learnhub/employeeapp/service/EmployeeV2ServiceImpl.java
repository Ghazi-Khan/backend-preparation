package com.learnhub.employeeapp.service;

import com.learnhub.employeeapp.entity.EmployeeEntity;
import com.learnhub.employeeapp.exception.EmployeeNotFoundException;
import com.learnhub.employeeapp.model.Employee;
import com.learnhub.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        List<EmployeeEntity> employeeEntities =
                employeeRepository.findAll();

        return employeeEntities
                .stream()
                .map(employeeEntity -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(employeeEntity, employee);
                    return employee;
                })
                .toList();

    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        Optional<EmployeeEntity> employeeEntityOptional =
                employeeRepository.findById(employeeId);

        employeeEntityOptional
                .orElseThrow(
                        () -> new EmployeeNotFoundException("Employee not found with id : " + employeeId )
                );

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntityOptional.get(), employee);
        return employee;
    }

    @Override
    public String deleteEmployeeById(String employeeId) {
        employeeRepository.deleteById(employeeId);
        return "Employee with id " + employeeId + " deleted successfully";
    }
}

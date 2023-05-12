package com.paul.shoewearbackend.services;

import com.paul.shoewearbackend.entities.EmployeeEntity;
import com.paul.shoewearbackend.model.Employee;
import com.paul.shoewearbackend.repository.EmployeeRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    @Override
    public Employee creatEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,employeeEntity);
        employeeRepo.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
        List<Employee> employees = employeeEntities
                .stream()
                .map(emp -> new Employee(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId()))
                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepo.findById(id).get();
        employeeRepo.delete(employee);
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepo.findById(id).get();
    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeEntity,employee);
    return employee;

    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepo.findById(id).get();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmailId(employee.getEmailId());
        employeeRepo.save(employeeEntity);
        return employee;
    }
}

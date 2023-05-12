package com.paul.shoewearbackend.services;

import com.paul.shoewearbackend.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee creatEmployee(Employee employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);
}

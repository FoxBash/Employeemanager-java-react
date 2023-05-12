package com.paul.shoewearbackend.controller;

import com.paul.shoewearbackend.entities.EmployeeEntity;
import com.paul.shoewearbackend.model.Employee;
import com.paul.shoewearbackend.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

private final EmployeeService employeeService;
@Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,employeeEntity);
    return employeeService.creatEmployee(employee);

    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
    return employeeService.getAllEmployees();
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
    boolean deleted = false;
    deleted =employeeService.deleteEmployee(id);
        Map<String, Boolean> response  = new HashMap<>();
        response.put("deleted",deleted);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
    Employee employee = null;
    employee = employeeService.getEmployeeById(id);
    return ResponseEntity.ok(employee);
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
    employee = employeeService.updateEmployee(id,employee);
    return ResponseEntity.ok(employee);
    }
}

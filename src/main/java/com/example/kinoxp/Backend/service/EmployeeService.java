package com.example.kinoxp.Backend.service;


import com.example.kinoxp.Backend.model.Employee;

import com.example.kinoxp.Backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee authenticateEmployee(String username, String password) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }







    public Optional<Employee> getEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(int employeeId, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
        if (existingEmployee.isPresent()) {
            updatedEmployee.setEmployeeId(employeeId);
            return Optional.of(employeeRepository.save(updatedEmployee));
        } else {
            return Optional.empty();
        }
    }


    public Optional<Employee> findByEmployeeId(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public void deleteByEmployeeId(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }


//query custom m jpa



    public boolean deleteEmployee(int employeeId) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeId);
        if (existingEmployee.isPresent()) {
            employeeRepository.deleteById(employeeId);
            return true;
        } else {
            return false;
        }
    }

























}

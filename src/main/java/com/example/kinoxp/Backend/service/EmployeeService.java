/*
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


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }


    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(int employeeId, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findByEmployeeId(employeeId);

        if (existingEmployee != null) {
            existingEmployee.setEmployee_name(updatedEmployee.getEmployee_name());
            existingEmployee.setEmployee_mail(updatedEmployee.getEmployee_mail());
            existingEmployee.setEmployee_phoneNo(updatedEmployee.getEmployee_phoneNo());
            // You can update other fields here

            return employeeRepository.save(existingEmployee);
        }

        return null; // Employee not found
    }

    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }





}

 */

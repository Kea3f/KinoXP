package com.example.kinoxp.Backend.repositories;

import com.example.kinoxp.Backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByUsername(String username);

    Employee findByEmployeeId(int employeeId);

    //Employee findEmployeeByEmployeeId(int employeeId);

    //List<Employee> findAllEmployee_name(String employee_name); //All employees
}

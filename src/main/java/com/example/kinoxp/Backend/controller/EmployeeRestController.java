package com.example.kinoxp.Backend.controller;

import com.example.kinoxp.Backend.dto.LoginDto;
import com.example.kinoxp.Backend.model.Employee;
import com.example.kinoxp.Backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin // Jeg skal kunne tilgå min restcontroller gennem noget andet. I dette tilfælde javascript
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/test")
    public String test(){
        return "Test";
    }
    @PostMapping("/login")
    public ResponseEntity<Employee> authenticateEmployee(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto.getUsername());
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        Employee authenticatedEmployee = employeeService.authenticateEmployee(username, password);

        if (authenticatedEmployee != null) {
            return ResponseEntity.ok(authenticatedEmployee);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    /*


    @GetMapping("/employees") //Retrieve a list of all employees
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    */

    @GetMapping("/employee/{employeeId}") // Retrieve an employee by their ID.
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);

        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/employee") //Create a new employee.
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employee/{employeeId}") //Update an existing employee by their ID.
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee updatedEmployee) {
        Optional<Employee> employee = employeeService.updateEmployee(employeeId, updatedEmployee);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/employee/{employeeId}") //Delete an employee by their ID.
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
        if (employeeService.deleteEmployee(employeeId)) {
            return ResponseEntity.ok("Employee deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

/*

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
        Optional<Employee> orgStudent = EmployeeService.findByEmployeeId(employeeId);
        if (orgStudent.isPresent()) {
            EmployeeService.deleteByEmployeeId(employeeId);
            return ResponseEntity.ok("Employee deleted");
        } else {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

 */








}

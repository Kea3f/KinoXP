
package com.example.kinoxp.Backend.controller;


import com.example.kinoxp.Backend.dto.LoginDto;
import com.example.kinoxp.Backend.model.Employee;
import com.example.kinoxp.Backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/employees") //In terms of url and code, it would be "easier" to do without employees
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //Login
    @PostMapping("/login")
    public ResponseEntity<Employee> authenticateEmployee(@RequestBody LoginDto loginDto, HttpSession httpSession) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        Employee authenticatedEmployee = employeeService.authenticateEmployee(username, password);

        if (authenticatedEmployee != null) {
            httpSession.setAttribute("employeeId", authenticatedEmployee.getEmployeeId());
            return ResponseEntity.ok(authenticatedEmployee);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //viewing all employees
    @GetMapping("/employeeList")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    //Get specific employee information
    @GetMapping("/employeeInfo/{employeeId}")
    public ResponseEntity<Employee> getEmployeeInfo(@PathVariable int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }



    //Create employee
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    //Edit employee
    @PutMapping("/editEmployee/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeService.updateEmployee(employeeId, updatedEmployee);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }




    //Delete employee
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted");
    }

}



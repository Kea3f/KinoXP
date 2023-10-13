
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


    @GetMapping("/employeeList")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }



    @GetMapping("/employeeInfo{employeeId}")
    public ResponseEntity<Employee> getEmployeeInfo(@PathVariable int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/editEmployee{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeService.updateEmployee(employeeId, updatedEmployee);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted");
    }

}



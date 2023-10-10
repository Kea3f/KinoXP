package com.example.kinoxp.Backend.controller;

import com.example.kinoxp.Backend.model.Employee;
import com.example.kinoxp.Backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/login") //Display for the frontpage
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/homepage")
    public String showHomepage() {
        return "homepage"; // This should match the name of your HTML template
    }

    @PostMapping("/login")
    public String login(@RequestParam("employeeId") int employeeId, @RequestParam("password") String password, HttpSession session, Model model) {
        try {
            Employee employee = employeeRepository.findById(employeeId).orElse(null);
            if (employee == null || !employee.getPassword().equals(password)) {
                model.addAttribute("error", "Employee ID or password is incorrect");
                return "login"; // Return to the login page with an error message
            }

            // Set a session attribute to indicate that the user is logged in (you can customize this)
            session.setAttribute("loggedIn", true);

            // Redirect to the homepage
            return "redirect:/homepage"; // Assuming "homepage" is the URL mapping for your homepage
        } catch (Exception e) {
            model.addAttribute("error", "Employee ID or password is incorrect");
            return "login"; // Return to the login page with an error message
        }
    }


    @GetMapping("/employees") // Display the employee list page
    public String showEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employeeList"; // Assuming you have an "employeeList.html" template
    }

    @GetMapping("/employees/{id}") // View a specific employee
    public String viewEmployee(@PathVariable int id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            model.addAttribute("employee", employee);
        }
        return "employeeDetails"; // Assuming you have an "employeeDetails.html" template
    }


    @GetMapping("/employees/edit/{id}") // Edit a specific employee
    public String editEmployee(@PathVariable int id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            model.addAttribute("employee", employee);
        }
        return "editEmployee"; // Assuming you have an "editEmployee.html" template
    }


    @PostMapping("/employees/save") // Save edited employee details
    public String saveEmployee(@ModelAttribute Employee employee) {
        // Implement logic to save the edited employee details to the database
        // Redirect to the employee list page after saving
        employeeRepository.save(employee);
        return "redirect:/employees";
    }


    @GetMapping("/employees/delete/{id}") // Delete a specific employee
    public String deleteEmployee(@PathVariable int id) {
        // Implement logic to delete the employee from the database
        employeeRepository.deleteById(id);
        // Redirect to the employee list page after deleting
        return "redirect:/employees";
    }


    @GetMapping("/logout") // Log out the user
    public String logout(HttpSession session) {
        // Invalidate the session to log out the user
        session.invalidate();
        return "redirect:/login"; // Redirect to the login page after logout
    }

    @GetMapping("/EmployeeList")
    public String showEmployeeList() {
        return "employeeList";
    }




}

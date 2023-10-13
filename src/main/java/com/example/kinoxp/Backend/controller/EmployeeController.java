package com.example.kinoxp.Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    @GetMapping("/login")
    public String loginDisplay() {
        return "login";
    }

    @GetMapping("/homepage")
    public String homepageDisplay(){
        return "homepage";
    }


    @GetMapping("/employeeInfo{employeeId}")
    public String employeeinfoDisplay(){
        return "employeeInfo";
    }

    @GetMapping("/employeeList")
    public String employeeListDisplay(){
        return "employeeList";
    }

    @GetMapping("/menuBar")
    public String getMenuBar() {
        return "menuBar";
    }


    @GetMapping("/deleteEmployee")
    public String deleteEmployee(){
        return "deleteEmployee";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(){
        return "createEmployee";
    }

    @GetMapping()
    public String editEmployee(){
        return "editEmpployee";
    }







}

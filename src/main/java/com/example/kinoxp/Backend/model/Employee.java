package com.example.kinoxp.Backend.model;

import javax.persistence.*;

@Entity
public class Employee {

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String password;
    private String employee_name;
    private int employee_phoneNo;
    private String employee_mail;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_phoneNo() {
        return employee_phoneNo;
    }

    public void setEmployee_phoneNo(int employee_phoneNo) {
        this.employee_phoneNo = employee_phoneNo;
    }

    public String getEmployee_mail() {
        return employee_mail;
    }

    public void setEmployee_mail(String employee_mail) {
        this.employee_mail = employee_mail;
    }

    /*
    //many employees can be associated with many movies
    @ManyToMany(mappedBy = "employees")
    private List<MovieModel> movies;
*/
}
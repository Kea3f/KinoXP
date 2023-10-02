package com.example.kinoxp.Backend.model;

import jakarta.persistence.*;

@Entity
public class EmployeeModel {

    @Id
    @Column(length = 2)
    private int employee_number;
    private String password;

    public int getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(int employee_number) {
        this.employee_number = employee_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}









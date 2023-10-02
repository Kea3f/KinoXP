package com.example.kinoxp.Backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LoginDto {

    @Id
    @Column (length = 2)
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

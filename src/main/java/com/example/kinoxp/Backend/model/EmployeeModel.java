package com.example.kinoxp.Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int employee_number_id;
    private String password;
    private String employee_name;
    private String






}

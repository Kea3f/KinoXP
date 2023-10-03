package com.example.kinoxp.Backend.configClass;

import com.example.kinoxp.Backend.model.EmployeeModel;
import com.example.kinoxp.Backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Test data
@Component
public class InitData implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        EmployeeModel employee1 = new EmployeeModel();
        employee1.setEmployee_number(1);
        employee1.setPassword("Kea1234.");
        employee1.setEmployee_name("Naja M.");
        employee1.setEmployee_mail("najamoe@outlook.dk");
        employee1.setEmployee_phoneNo(62622362);

        // Save the employee to the database using the repository
        employeeRepository.save(employee1);


        EmployeeModel employee2 = new EmployeeModel();
        employee2.setEmployee_number(2);
        employee2.setPassword("Kea1234.");
        employee2.setEmployee_name("Sabrina E.");
        employee2.setEmployee_mail("Sabrina.ebbesen@gmail.com");
        employee2.setEmployee_phoneNo(27710977);

        // Save the employee to the database using the repository
        employeeRepository.save(employee2);


        EmployeeModel employee3 = new EmployeeModel();
        employee3.setEmployee_number(3);
        employee3.setPassword("Kea1234.");
        employee3.setEmployee_name("Heval P.");
        employee3.setEmployee_mail("HevalP@outlook.dk");
        employee3.setEmployee_phoneNo(23263981);

        // Save the employee to the database using the repository
        employeeRepository.save(employee3);


        EmployeeModel employee4 = new EmployeeModel();
        employee4.setEmployee_number(4);
        employee4.setPassword("Kea1234.");
        employee4.setEmployee_name("Mathilde T.");
        employee4.setEmployee_mail("Trendy@gmail.com");
        employee4.setEmployee_phoneNo(25263840);

        // Save the employee to the database using the repository
        employeeRepository.save(employee4);



    }

}

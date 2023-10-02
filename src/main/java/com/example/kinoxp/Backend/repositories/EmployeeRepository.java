package com.example.kinoxp.Backend.repositories;

import com.example.kinoxp.Backend.model.EmployeeModel;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class EmployeeRepository {

    private final DataSource dataSource;

    public EmployeeRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }


    public EmployeeModel login(String employee_number, String password) throws SQLException {
        Connection con = dataSource.getConnection();

    }
}

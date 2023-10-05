package com.example.kinoxp.Backend.repositories;

import com.example.kinoxp.Backend.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
EmployeeModel findAllById(int id);

}



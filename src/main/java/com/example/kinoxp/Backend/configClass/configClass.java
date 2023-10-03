package com.example.kinoxp.Backend.configClass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class configClass {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/kinohoes");
        dataSource.setUsername("SabrinaMathilde");
        dataSource.setPassword("Kea1234.");
        return dataSource;
    }
}

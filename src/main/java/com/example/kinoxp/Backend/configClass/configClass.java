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
       // dataSource.setUrl(INDSÆT URL);
        // dataSource.setUsername("root")
        //dataSource.setPassword("1234);
        return dataSource;
    }
}

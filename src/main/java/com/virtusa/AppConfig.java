package com.virtusa;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig {

  @Bean
  public DataSource dataSource() {
        final DriverManagerDataSource dataSource = 
                     new DriverManagerDataSource();
         dataSource.setDriverClassName("com.mysql.jdbc.Driver");
         dataSource.setUrl("jdbc:mysql://localhost/customerlookup");
         dataSource.setUsername("root");
         dataSource.setPassword("password");
        
        return dataSource;
  }

  
}

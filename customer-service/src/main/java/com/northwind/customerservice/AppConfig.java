package com.northwind.customerservice;

import com.northwind.customerservice.repositories.CustomerRepository;
import com.northwind.customerservice.repositories.impl.AddressRowMapper;
import com.northwind.customerservice.repositories.impl.CustomerRowMapper;
import com.northwind.customerservice.repositories.impl.InMemoryCustomerRepository;
import com.northwind.customerservice.repositories.impl.MySqlCustomerRepository;
import com.northwind.customerservice.services.CustomerService;
import com.northwind.shipping.domain.PackingSlip;
import com.northwind.shipping.repositories.PackingSlipRepository;
import com.northwind.shipping.repositories.impl.MySqlPackingSlipRepository;
import com.northwind.shipping.repositories.impl.PackingSlipRowMapper;
import com.northwind.shipping.services.PackingSlipServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.northwind.shipping"})
public class AppConfig {

    @Bean
    public DataSource datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/shipping-db");
        dataSource.setUsername("shipping-user");
        dataSource.setPassword("password");

        return dataSource;
    }
    //DI Configuration goes here.
    @Bean
    public PackingSlipServices packingSlipService(PackingSlipRepository packingSlipRepository) {
        return new PackingSlipServices(packingSlipRepository);
    }

    @Bean
    public PackingSlipRepository packingSlipRepository(DataSource dataSource,
                                                       PackingSlipRowMapper packingSlipRowMapper) {
        return new MySqlPackingSlipRepository(dataSource, packingSlipRowMapper);
    }

    @Bean
    public PackingSlipRowMapper packingSlipRowMapper() {
        return new PackingSlipRowMapper();
    }


}
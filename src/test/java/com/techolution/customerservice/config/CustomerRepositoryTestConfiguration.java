package com.techolution.customerservice.config;

import com.techolution.customerservice.repository.CustomerRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CustomerRepositoryTestConfiguration {
    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepository();
    }
}

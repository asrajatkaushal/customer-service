package com.techolution.customerservice.repository;

import com.techolution.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}

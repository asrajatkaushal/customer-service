package com.techolution.customerservice.service;

import com.techolution.customerservice.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(long id);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
}

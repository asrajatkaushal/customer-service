package com.techolution.customerservice.service;

import com.techolution.customerservice.model.Customer;
import com.techolution.customerservice.repository.CustomerDAO;
import com.techolution.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDAO customerDAO;

    public List<Customer> search() {
        return customerRepository.getAllCustomers();
    }

    public Customer searchById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    public Customer searchByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    public Customer create(Customer customer) {
        return customerRepository.insert(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerDAO.findOne(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customerDAO.saveAndFlush(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customerDAO.saveAndFlush(customer);
        return customer;
    }
}

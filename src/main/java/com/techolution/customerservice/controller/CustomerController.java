package com.techolution.customerservice.controller;

import com.techolution.customerservice.model.Customer;
import com.techolution.customerservice.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping
    public List<Customer> search() {
        return customerServiceImpl.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer searchById(@PathVariable Long id) {
        return customerServiceImpl.getCustomerById(id);
    }

    @GetMapping("/email/{emailId:.+}")
    public Customer searchByEmail(@PathVariable String emailId) {
        return customerServiceImpl.getCustomerByEmail(emailId);
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerServiceImpl.saveCustomer(customer);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return customerServiceImpl.updateCustomer(customer);
    }
}

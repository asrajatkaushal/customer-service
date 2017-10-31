package com.techolution.customerservice.repository;

import com.techolution.customerservice.config.CustomerRepositoryTestConfiguration;
import com.techolution.customerservice.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = CustomerRepositoryTestConfiguration.class)
public class CustomerRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testInsert() {
        Customer customer = Customer.builder()
                .firstName("abc").lastName("xyz").username("abc.xyz").password("abcXYZ").email("abc.xyz@test.com").build();

        Customer actualCustomer = customerRepository.insert(customer);

        assertThat(actualCustomer.getFirstName(), equalTo(customer.getFirstName()));
        assertThat(actualCustomer.getLastName(), equalTo(customer.getLastName()));
        assertThat(actualCustomer.getUsername(), equalTo(customer.getUsername()));
        assertThat(actualCustomer.getEmail(), equalTo(customer.getEmail()));
        assertThat(actualCustomer.getPassword(), equalTo(customer.getPassword()));
    }

    @Test
    public void testUpdate() {
        Customer customer = Customer.builder()
                .firstName("abc").lastName("xyz").username("abc.xyz").password("abcXYZ").email("abc.xyz@test.com").build();

        Customer insertedCustomer = customerRepository.insert(customer);
        insertedCustomer.setFirstName("abcd");
        Customer updatedCustomer = customerRepository.update(insertedCustomer);

        assertEquals(customer.getFirstName(), updatedCustomer.getFirstName());
        assertEquals(customer.getLastName(), updatedCustomer.getLastName());
        assertEquals(customer.getUsername(), updatedCustomer.getUsername());
        assertEquals(customer.getEmail(), updatedCustomer.getEmail());
        assertEquals(customer.getPassword(), updatedCustomer.getPassword());
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer = Customer.builder()
                .firstName("abc").lastName("xyz").username("abc.xyz").password("abcXYZ").email("abc.xyz@test.com").build();

        Customer insertedCustomer = customerRepository.insert(customer);
        List expectedCustomers = customerRepository.getAllCustomers();
    }
}

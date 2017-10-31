package com.techolution.customerservice.service;

import com.techolution.customerservice.model.Customer;
import com.techolution.customerservice.repository.CustomerDAO;
import com.techolution.customerservice.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    public void testSearch() {
        List expectedCustomers = getCustomers();
        doReturn(expectedCustomers).when(customerRepository).getAllCustomers();
        List actualCustomers = customerServiceImpl.search();
        assertEquals(expectedCustomers, actualCustomers);
        verify(customerRepository, times(1)).getAllCustomers();
    }

    @Test
    public void testSearchById() {
        Customer expectedCustomer = getCustomer();
        doReturn(expectedCustomer).when(customerRepository).getCustomerById(anyLong());
        Customer actualCustomer = customerServiceImpl.searchById(1L);
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerRepository, times(1)).getCustomerById(anyLong());
    }

    @Test
    public void testSearchByEmail() {
        Customer expectedCustomer = getCustomer();
        doReturn(expectedCustomer).when(customerRepository).getCustomerByEmail(anyString());
        Customer actualCustomer = customerServiceImpl.searchByEmail("abc.pqrs@test.com");
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerRepository, times(1)).getCustomerByEmail(anyString());
    }

    @Test
    public void testCreate() {
        Customer expectedCustomer = getCustomer();
        doReturn(expectedCustomer).when(customerRepository).insert(any(Customer.class));
        Customer actualCustomer = customerServiceImpl.create(expectedCustomer);
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerRepository, times(1)).insert(any(Customer.class));
    }

    @Test
    public void testUpdate() {
        Customer expectedCustomer = getCustomer();
        doReturn(expectedCustomer).when(customerRepository).update(any(Customer.class));
        Customer actualCustomer = customerServiceImpl.update(expectedCustomer);
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerRepository, times(1)).update(any(Customer.class));
    }

    @Test
    public void testGetAllCustomers() {
        List expectedCustomers = getCustomers();
        doReturn(expectedCustomers).when(customerDAO).findAll();
        List actualCustomers = customerServiceImpl.getAllCustomers();
        assertEquals(expectedCustomers, actualCustomers);
        verify(customerDAO, times(1)).findAll();
    }

    @Test
    public void testGetCustomerById() {
        Customer expectedCustomer = getCustomer();
        doReturn(expectedCustomer).when(customerDAO).findOne(anyLong());
        Customer actualCustomer = customerServiceImpl.getCustomerById(1L);
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerDAO, times(1)).findOne(anyLong());
    }

    @Test
    public void testGetCustomerByEmail() {
        Customer expectedCustomer = getCustomer();
        doReturn(expectedCustomer).when(customerDAO).findByEmail(anyString());
        Customer actualCustomer = customerServiceImpl.getCustomerByEmail("abc.pqrs@test.com");
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerDAO, times(1)).findByEmail(anyString());
    }

    @Test
    public void testSaveCustomer() {
        Customer expectedCustomer = getCustomer();
        doReturn(expectedCustomer).when(customerDAO).saveAndFlush(any(Customer.class));
        Customer actualCustomer = customerServiceImpl.saveCustomer(expectedCustomer);
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerDAO, times(1)).saveAndFlush(any(Customer.class));
    }

    @Test
    public void testUpdateCustomer() {
        Customer expectedCustomer = getCustomer();
        doReturn(expectedCustomer).when(customerDAO).saveAndFlush(any(Customer.class));
        Customer actualCustomer = customerServiceImpl.updateCustomer(expectedCustomer);
        assertEquals(expectedCustomer, actualCustomer);
        verify(customerDAO, times(1)).saveAndFlush(any(Customer.class));
    }

    private Customer getCustomer() {
        return Customer.builder().id(1L).firstName("ABC").lastName("PQRS").email("abc.pqrs@test.com").username("abc.pqrs")
                .password("abc.pqrs").build();
    }

    private List getCustomers() {
        return new ArrayList<Customer>(){{
            add(Customer.builder().id(1L).firstName("ABC").lastName("PQRS").email("abc.pqrs@test.com").username("abc.pqrs")
                    .password("abc.pqrs").build());
            add(Customer.builder().id(2L).firstName("DEF").lastName("WXYZ").email("def.wxyz@test.com").username("def.wxyz")
                    .password("def.wxyz").build());
        }};
    }
}

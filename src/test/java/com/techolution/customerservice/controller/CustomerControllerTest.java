package com.techolution.customerservice.controller;

import com.techolution.customerservice.model.Customer;
import com.techolution.customerservice.service.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerServiceImpl;

    @Test
    public void testSearch() throws Exception {
        List customers = getCustomers();
        String fileName = "customers.json";
        String expectedResult = getFileContent(fileName);

        doReturn(customers).when(customerServiceImpl).getAllCustomers();

        mockMvc.perform(get("/customers/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResult));

        verify(customerServiceImpl, times(1)).getAllCustomers();
    }

    @Test
    public void testSearchById() throws Exception {
        Customer customer = getCustomer();
        String expectedResult = getFileContent("customer.json");

        doReturn(customer).when(customerServiceImpl).getCustomerById(anyLong());

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResult));

        verify(customerServiceImpl, times(1)).getCustomerById(anyLong());
    }

    @Test
    public void testSearchByEmail() throws Exception {
        Customer customer = getCustomer();
        String expectedResult = getFileContent("customer.json");

        doReturn(customer).when(customerServiceImpl).getCustomerByEmail(anyString());

        mockMvc.perform(get("/customers/email/abc.pqrs@test.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResult));

        verify(customerServiceImpl, times(1)).getCustomerByEmail(anyString());
    }

    @Test
    public void testCreate() throws Exception {
        Customer customer = getCustomer();
        String expectedResult = getFileContent("customer.json");

        doReturn(customer).when(customerServiceImpl).saveCustomer(any(Customer.class));

        mockMvc.perform(post("/customers")
                    .content(getFileContent("customer.json")).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResult));

        verify(customerServiceImpl, times(1)).saveCustomer(any(Customer.class));
    }

    @Test
    public void testUpdate() throws Exception {
        Customer customer = getCustomer();
        String expectedResult = getFileContent("customer.json");

        doReturn(customer).when(customerServiceImpl).updateCustomer(any(Customer.class));

        mockMvc.perform(put("/customers")
                    .content(getFileContent("customer.json")).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResult));

        verify(customerServiceImpl, times(1)).updateCustomer(any(Customer.class));
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

    private String getFileContent(String fileName) throws IOException {
        String path = "com/techolution/customerservice/controller/" + fileName;
        ClassLoader classLoader = getClass().getClassLoader();
        return new String(Files.readAllBytes(new File(classLoader.getResource(path).getFile()).toPath()));
    }
}

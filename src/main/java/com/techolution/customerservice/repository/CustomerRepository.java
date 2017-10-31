package com.techolution.customerservice.repository;

import com.techolution.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Customer insert(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    public Customer update(Customer customer) {
        entityManager.merge(customer);
        return customer;
    }

    public List getAllCustomers() {
        String hql = "FROM Customer ORDER BY firstName, lastName";
        return entityManager.createQuery(hql).getResultList();
    }

    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public Customer getCustomerByEmail(String email) {
        String hql = "FROM Customer WHERE email = :email";
        return (Customer) entityManager.createQuery(hql).setParameter("email", email).getSingleResult();
    }

}

package com.codechallenge.keola.api.domain.customer;

import java.util.List;

public interface CustomerRepository {

    boolean save(Customer customer);
    boolean delete(String customerId);
    boolean update(Customer customer, String customerId);
    Customer findById(String customerId);
    List<Customer> findAll();

}

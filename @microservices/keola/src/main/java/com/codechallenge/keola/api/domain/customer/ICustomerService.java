package com.codechallenge.keola.api.domain.customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Optional<Customer> getById(Long id);
    List<Customer> getAll();
    boolean create(Customer customer);
    boolean update(Long id, Customer customer);
    boolean delete(Long id);
}
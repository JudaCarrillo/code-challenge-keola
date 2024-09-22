package com.codechallenge.keola.api.domain.customer;

import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer, String customerId) {
        customerRepository.update(customer, customerId);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.delete(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }

}

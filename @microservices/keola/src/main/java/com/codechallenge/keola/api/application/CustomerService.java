package com.codechallenge.keola.api.application;

import com.codechallenge.keola.api.domain.customer.Customer;
import com.codechallenge.keola.api.domain.customer.ICustomerRepository;
import com.codechallenge.keola.api.domain.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public boolean create(Customer customer) {
        Optional<Customer> customerExist = customerRepository.findCustomerByEmail(customer.getEmail());

        if (customerExist.isPresent()) {
            return false;
        }

        customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean update(Long id, Customer customer) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);

        if (existingCustomerOpt.isEmpty()) {
            return false;
        }

        Customer existingCustomer = existingCustomerOpt.get();

        existingCustomer.setName(customer.getName());
        existingCustomer.setLastname(customer.getLastname());
        existingCustomer.setPhone(customer.getPhone());

        String newEmail = customer.getEmail();
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(newEmail);

        if (customerByEmail.isPresent() && !customerByEmail.get().getCustomerId().equals(id)) {
            return false;
        }

        existingCustomer.setEmail(newEmail);

        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Customer> customerExist = customerRepository.findById(id);

        if (customerExist.isEmpty()) {
            return false;
        }

        customerRepository.deleteById(id);
        return true;
    }

}

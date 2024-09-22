package com.codechallenge.keola.api.infrastructure.controllers.customer;

import com.codechallenge.keola.api.domain.customer.Customer;
import com.codechallenge.keola.api.domain.customer.CustomerService;
import com.codechallenge.keola.api.infrastructure.persistence.CustomerRepositoryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepositoryImpl customerRepositoryImpl = new CustomerRepositoryImpl();
    private final CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService(this.customerRepositoryImpl);
    }

    @GetMapping("/")
    public String getCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.toString();
    }
}

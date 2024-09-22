package com.codechallenge.keola.customer;

import com.codechallenge.keola.api.application.CustomerService;
import com.codechallenge.keola.api.domain.customer.Customer;
import com.codechallenge.keola.api.domain.customer.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    ICustomerRepository customerRepository;
    CustomerService customerService;

    @BeforeEach
    public void setUp() {
        this.customerRepository = Mockito.mock(ICustomerRepository.class);
        this.customerService = new CustomerService(customerRepository);
    }

    @Test
    void testCreateCustomer() {

        Customer customer = new Customer();
        customer.setName("John");
        customer.setLastname("Doe");
        customer.setEmail("hola@gmail.com");

        when(customerRepository.save(customer)).thenReturn(customer);

        boolean result = customerService.create(customer);
        assertTrue(result, "El cliente debería haberse creado correctamente.");
    }


}

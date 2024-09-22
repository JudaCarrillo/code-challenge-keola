package com.codechallenge.keola.customer;

import com.codechallenge.keola.api.domain.customer.Customer;
import com.codechallenge.keola.api.domain.customer.CustomerRepository;
import com.codechallenge.keola.api.domain.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void registerCustomer_ShouldSaveUser() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("Juan");
        customer.setLastname("Perez");
        customer.setEmail("juan@gmail.com");
        customer.setPhone("123456789");

        // Act
        customerService.registerCustomer(customer);

        // Assert
        verify(customerRepository, times(1)).save(customer);
    }

}

package com.codechallenge.keola.api.infrastructure.controllers.customer;

import com.codechallenge.keola.api.application.CustomerService;
import com.codechallenge.keola.api.domain.customer.Customer;
import com.codechallenge.keola.api.utils.ApiResponse;
import com.codechallenge.keola.api.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getCustomers() {
        List<Customer> customers = customerService.getAll();
        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Customers found", customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getById(id);
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().body(ApiResponse.createErrorResponse("Customer not found", ErrorCodes.CUSTOMER_NOT_FOUND.getCode()));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Customer found", customer.get()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody Customer customer) {
        boolean success = customerService.create(customer);

        if (!success) {
            return ResponseEntity.badRequest().body(ApiResponse.createErrorResponse("Customer already exists", ErrorCodes.CUSTOMER_EXISTS.getCode()));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Customer created successfully", customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        boolean success = customerService.update(id, customer);

        if (!success) {
            return ResponseEntity.badRequest().body(ApiResponse.createErrorResponse("Customer not updated", ErrorCodes.CUSTOMER_NOT_UPDATED.getCode()));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Customer updated successfully", customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) {
        boolean success = customerService.delete(id);

        if (!success) {
            return ResponseEntity.badRequest().body(ApiResponse.createErrorResponse("Customer not found", ErrorCodes.CUSTOMER_NOT_FOUND.getCode()));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Customer deleted successfully", null));
    }
}

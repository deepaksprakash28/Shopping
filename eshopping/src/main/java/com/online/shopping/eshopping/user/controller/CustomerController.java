package com.online.shopping.eshopping.user.controller;

import com.online.shopping.eshopping.user.model.Customer;
import com.online.shopping.eshopping.user.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        customerService.registerCustomer(customer);
        return ResponseEntity.ok("Customer registered successfully!");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Customer> getCustomerByPhone(@PathVariable String phone) {
        Optional<Customer> customer = customerService.getCustomerByPhone(phone);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

package com.online.shopping.eshopping.user.service;

import com.online.shopping.eshopping.user.model.Customer;
import com.online.shopping.eshopping.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        if (customerRepository.existsByPhone(customer.getPhone())) {
            throw new RuntimeException("Phone number already in use");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
}

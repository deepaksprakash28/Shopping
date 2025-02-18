package com.online.shopping.eshopping.user.service;

import com.online.shopping.eshopping.user.model.Customer;
import java.util.Optional;

public interface ICustomerService {

    Customer registerCustomer(Customer customer);

    Optional<Customer> getCustomerByEmail(String email);

    Optional<Customer> getCustomerByPhone(String phone);
}

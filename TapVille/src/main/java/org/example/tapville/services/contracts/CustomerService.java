package org.example.tapville.services.contracts;

import org.example.tapville.models.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getCustomerById(Long id);
    Customer getCustomerByName(String name);
    List<Customer>getAll();
    Customer promoteToAdmin(Customer customer,Customer admin);
    Customer demote(Customer customer, Customer admin);

}

package org.example.tapville.services;

import org.example.tapville.exceptions.InvalidOperationException;
import org.example.tapville.models.Customer;
import org.example.tapville.repositories.contracts.CustomerRepository;
import org.example.tapville.services.contracts.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CANNOT_PERFORM_OPERATION = "You can't perform this operation";

    private final CustomerRepository customerRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customerRepository.getCustomerByName(name);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Customer promoteToAdmin(Customer customer, Customer admin) {
        if (!admin.isAdmin()) {
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);

        }
        customer.setAdmin(true);
        return customerRepository.save(customer);
    }

    @Override
    public Customer demote(Customer customer, Customer admin) {
        if (!admin.isAdmin()) {
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
        customer.setAdmin(false);
        return customerRepository.save(customer);
    }
}

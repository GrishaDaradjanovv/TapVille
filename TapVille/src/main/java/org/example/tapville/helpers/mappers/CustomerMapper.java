package org.example.tapville.helpers.mappers;

import org.example.tapville.models.Customer;
import org.example.tapville.models.dtos.CustomerDto;
import org.example.tapville.services.contracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private final CustomerService customerService;

    @Autowired
    public CustomerMapper(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer createCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getCustomerFirstName());
        customer.setLastName(customerDto.getCustomerLastName());
        customer.setEmail(customerDto.getCustomerEmail());
        customer.setPhoneNumber(customerDto.getCustomerPhoneNumber());
        return customer;
    }
    public CustomerDto customerToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerFirstName(customer.getName());
        customerDto.setCustomerLastName(customer.getLastName());
        customerDto.setCustomerEmail(customer.getEmail());
        customerDto.setCustomerPhoneNumber(customer.getPhoneNumber());
        return customerDto;
    }

}

package org.example.tapville.controllers.rest;

import jakarta.validation.Valid;
import org.example.tapville.mappers.CustomerMapper;
import org.example.tapville.models.Customer;
import org.example.tapville.models.dtos.CustomerDto;
import org.example.tapville.services.contracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerRestController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    List<Customer>getAll(){
        return customerService.getAll();
    }

    @PostMapping
    public void create(@Valid @RequestBody CustomerDto customerDto){
        Customer customer =customerMapper.createCustomer(customerDto);
        customerService.create(customer);
    }
}

package org.example.tapville.repositories.contracts;

import org.example.tapville.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer getCustomerById(Long id);

    Customer getCustomerByName(String name);
}

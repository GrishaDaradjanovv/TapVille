package org.example.tapville.repositories.contracts;

import org.example.tapville.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer getCustomerById(Long id);

    Customer getCustomerByName(String name);
    @Query("select c from Customer c")
    List<Customer> getAll();
}

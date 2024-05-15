package org.example.tapville.repositories.contracts;

import org.example.tapville.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByBusinessName(String businessName);
    Business getBusinessById(Long id);
}

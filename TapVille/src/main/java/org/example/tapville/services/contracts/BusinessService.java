package org.example.tapville.services.contracts;

import org.example.tapville.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BusinessService {
    Business getById(Long id);

    Business create(Business business);

    Business update(Business business);

    void deleteById(Long id);

    List<Business> getAll();

    List<Business> getByName(Business business);

    List<Business> getAllActive();
}

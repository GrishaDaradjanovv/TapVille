package org.example.tapville.services;

import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.models.Business;
import org.example.tapville.repositories.contracts.BusinessRepository;
import org.example.tapville.services.contracts.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public Business getById(Long id) {
        Business businessOptional = businessRepository.getBusinessById(id);
        if (businessOptional != null && !businessOptional.isDeleted()) {
            return businessOptional;
        }
        throw new EntityNotFoundException("Business", "ID", String.valueOf(id));
    }

    @Override
    public Business create(Business business) {
        business.setBusinessCode(business.getBusinessCode());
        business.setCreationDate(new Timestamp(System.currentTimeMillis()));
        return businessRepository.save(business);
    }

    @Override
    public Business update(Business business) {
        Optional<Business> businessOptional = businessRepository.findById(business.getId());
        if (businessOptional.isPresent()) {
            return businessRepository.save(business);
        }
        throw new EntityNotFoundException("Business", "ID", String.valueOf(business.getId()));
    }


    @Override
    public void deleteById(Long id) {
        Business business = getById(id);
        business.setDeleted(true);
        businessRepository.save(business);
    }

    @Override
    public List<Business> getAll() {
        return businessRepository.findAll();
    }

    @Override
    public List<Business> getByName(Business business) {
        return businessRepository.findByBusinessName(business.getBusinessName());
    }

    @Override
    public List<Business> getAllActive() {
        return businessRepository.findAll();
    }
}

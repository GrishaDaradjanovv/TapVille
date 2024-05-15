package org.example.tapville.services;

import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.models.Business;
import org.example.tapville.repositories.contracts.BusinessRepository;
import org.example.tapville.services.contracts.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {

    private BusinessRepository businessRepository;
    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public Business getById(Long id) {
        Optional<Business> businessOptional = businessRepository.findById(id);
        if (businessOptional.isPresent()) {
            return businessOptional.get();
        }
        throw new EntityNotFoundException("Business", "ID", String.valueOf(id));
    }

    @Override
    public Business create(Business business) {
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
    public List<Business> getByName(String name) {
        return businessRepository.findByName(name);
    }

    @Override
    public List<Business> getAllActive() {
        return businessRepository.findAll();
    }
}

package org.example.tapville.controllers.rest;

import jakarta.validation.Valid;
import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.helpers.mappers.BusinessMapper;
import org.example.tapville.models.Business;
import org.example.tapville.models.dtos.BusinessDto;
import org.example.tapville.services.contracts.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
public class BusinessRestController {
    private final BusinessService businessService;
    private final BusinessMapper businessMapper;

    @Autowired
    public BusinessRestController(BusinessService businessService, BusinessMapper businessMapper) {
        this.businessService = businessService;
        this.businessMapper = businessMapper;
    }

    @GetMapping
    public List<Business> getAll() {
        return businessService.getAll();
    }

    @GetMapping("/{id}")
    public Business getById(@PathVariable long id) {
        try {
            return businessService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    @PostMapping("/create")
    public void create(@Valid @RequestBody BusinessDto businessDto) {
        try {
            Business business = businessMapper.dtoBusinessCreate(businessDto);
            businessService.create(business);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        try {
            businessService.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

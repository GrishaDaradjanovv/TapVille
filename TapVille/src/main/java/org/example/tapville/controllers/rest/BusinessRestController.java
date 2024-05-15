package org.example.tapville.controllers.rest;

import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.models.Business;
import org.example.tapville.services.contracts.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/businesses")
public class BusinessRestController {
    private final BusinessService businessService;

    @Autowired
    public BusinessRestController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping("/{id}")
    public Business getById(@PathVariable long id){
        try{
            return businessService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }

    }
}

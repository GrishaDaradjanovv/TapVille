package org.example.tapville.controllers.rest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.helpers.mappers.DiscountCardMapper;
import org.example.tapville.helpers.mappers.StampCardMapper;
import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.DiscountCard;
//import org.example.tapville.models.StampCard;
//import org.example.tapville.services.contracts.CardService;
import org.example.tapville.models.StampCard;
import org.example.tapville.models.dtos.DiscountCardDto;
import org.example.tapville.models.dtos.StampCardDto;
import org.example.tapville.services.contracts.BusinessService;
import org.example.tapville.services.contracts.CustomerService;
import org.example.tapville.services.contracts.DiscountCardService;
//import org.example.tapville.services.contracts.StampCardService;
import org.example.tapville.services.contracts.StampCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardRestController {
   private final StampCardService stampCardService;
    private final DiscountCardService discountCardService;
    private final BusinessService businessService;
    private final CustomerService customerService;
    private final StampCardMapper stampCardMapper;
    private final DiscountCardMapper discountCardMapper;
    @Autowired
    public CardRestController(StampCardService stampCardService, DiscountCardService discountCardService, BusinessService businessService, CustomerService customerService, StampCardMapper stampCardMapper, DiscountCardMapper discountCardMapper) {
        this.stampCardService = stampCardService;
        this.discountCardService = discountCardService;
        this.businessService = businessService;
        this.customerService = customerService;
        this.stampCardMapper = stampCardMapper;
        this.discountCardMapper = discountCardMapper;
    }
    @GetMapping("/stamps")
    public List<StampCard>getAllStampCards(){
        return stampCardService.getAll();
    }

    @GetMapping("/discounts")
    public List<DiscountCard>getAllDiscountCards(){
        return discountCardService.getAll();
    }
    @PostMapping("/stamp")
    public StampCardDto create(@Valid @RequestBody StampCardDto stampCardDto){
        try{
            Business creator = businessService.getById(1L);
            Customer owner = customerService.getCustomerById(1L);
            StampCard stampCard = stampCardMapper.createStampCard(stampCardDto,creator,owner);
            stampCardService.createStampCard(stampCard,creator,owner);
            return stampCardMapper.stampCardToDto(stampCard);
        }catch (Exception e){
            throw new InputMismatchException("PROBLEM");
        }
    }
    @PostMapping("/discount")
    public DiscountCardDto create(@Valid @RequestBody DiscountCardDto discountCardDto){
        try{
            Business creator = businessService.getById(1L);
            Customer owner = customerService.getCustomerById(1L);
            DiscountCard discountCard = discountCardMapper.createDiscountCard(discountCardDto,creator,owner);
            discountCardService.createDiscountCard(discountCard,discountCardDto.getDiscountPercentage(),creator,owner);
            return discountCardMapper.discountCardToDto(discountCard);
        }catch (Exception e){
            throw new InputMismatchException("PROBLEM");
        }
    }

}

package org.example.tapville.controllers.rest;

import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.models.DiscountCard;
//import org.example.tapville.models.StampCard;
//import org.example.tapville.services.contracts.CardService;
import org.example.tapville.models.StampCard;
import org.example.tapville.services.contracts.DiscountCardService;
//import org.example.tapville.services.contracts.StampCardService;
import org.example.tapville.services.contracts.StampCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardRestController {
   private final StampCardService stampCardService;
    private final DiscountCardService discountCardService;

    public CardRestController(StampCardService stampCardService, DiscountCardService discountCardService) {
        this.stampCardService = stampCardService;
        this.discountCardService = discountCardService;
    }
    @GetMapping("/stamps")
    public List<StampCard>getAllStampCards(){
        return stampCardService.getAll();
    }

    @GetMapping("/discounts")
    public List<DiscountCard>getAllDiscountCards(){
        return discountCardService.getAll();
    }

}

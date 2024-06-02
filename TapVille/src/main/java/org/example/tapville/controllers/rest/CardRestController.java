package org.example.tapville.controllers.rest;

import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.exceptions.InvalidOperationException;
import org.example.tapville.mappers.DiscountCardMapper;
import org.example.tapville.mappers.StampCardMapper;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public List<StampCard> getAllStampCards() {
        return stampCardService.getAll();
    }

    @GetMapping("/discounts")
    public List<DiscountCard> getAllDiscountCards() {
        return discountCardService.getAll();
    }

    @PostMapping("/stamp")
    public StampCardDto create(@Valid @RequestBody StampCardDto stampCardDto) {
        try {
            Business creator = businessService.getById(1L);
            Customer owner = customerService.getCustomerById(1L);
            StampCard stampCard = stampCardMapper.createStampCard(stampCardDto, creator, owner);
            stampCardService.createStampCard(stampCard, creator, owner);
            return stampCardMapper.stampCardToDto(stampCard);
        } catch (WriterException e) {
            throw new InputMismatchException(e.getMessage());
        } catch (IOException e) {
            throw new InputMismatchException(e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/discount")
    public DiscountCardDto create(@Valid @RequestBody DiscountCardDto discountCardDto) {
        try {
            Business creator = businessService.getById(1L);
            Customer owner = customerService.getCustomerById(1L);
            DiscountCard discountCard = discountCardMapper.createDiscountCard(discountCardDto, creator, owner);
            discountCardService.createDiscountCard(discountCard, discountCardDto.getDiscountPercentage(), creator, owner);
            return discountCardMapper.discountCardToDto(discountCard);
        } catch (WriterException e) {
            throw new InputMismatchException(e.getMessage());
        } catch (IOException e) {
            throw new InputMismatchException(e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping("/stamp/add/{id}")
    public void addStamp(@PathVariable Long id) {
        Business creator = businessService.getById(1L);
        try {
            StampCard stampCard = stampCardService.getById(id);
            stampCardService.addStamp(stampCard, creator);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping("/stamp/remove/{id}")
    public void removeStamp(@PathVariable Long id) {
        Business creator = businessService.getById(1L);
        try {
            StampCard stampCard = stampCardService.getById(id);
            stampCardService.removeStamp(stampCard, creator);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidOperationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping("/stamp/qr/{id}")
    public ResponseEntity<Resource> getStampCardQrCode(@PathVariable Long id) {
        StampCard stampCard = stampCardService.getById(id);
        Path qrCodePath = Paths.get(stampCard.getPath());
        Resource qrCodeImage;
        try {
            qrCodeImage = new UrlResource(qrCodePath.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "QR code not found");
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(qrCodeImage);
    }
    @GetMapping("/discount/qr/{id}")
    public ResponseEntity<Resource> getDiscountCardQrCode(@PathVariable Long id) {
        DiscountCard discountCard = discountCardService.getById(id);
        Path qrCodePath = Paths.get(discountCard.getPath());
        Resource qrCodeImage;
        try {
            qrCodeImage = new UrlResource(qrCodePath.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "QR code not found");
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(qrCodeImage);
    }
}

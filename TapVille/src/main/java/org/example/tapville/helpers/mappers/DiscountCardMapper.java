package org.example.tapville.helpers.mappers;

import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.DiscountCard;
import org.example.tapville.models.dtos.DiscountCardDto;
import org.example.tapville.services.contracts.CardService;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardMapper extends CardMapper {
    public DiscountCardMapper(CardService cardService) {
        super(cardService);
    }
    public DiscountCard createDiscountCard(DiscountCardDto cardDto, Business business, Customer customer){
        DiscountCard discountCard = new DiscountCard();
        discountCard.setCardName(cardDto.getCardName());
        discountCard.setCreator(business);
        discountCard.setOwner(customer);
        discountCard.setDiscount(cardDto.getDiscountPercentage());
        return discountCard;
    }

    public DiscountCardDto discountCardToDto(DiscountCard discountCard){
        DiscountCardDto discountCardDto = new DiscountCardDto();
        discountCardDto.setCardName(discountCard.getCardName());
        discountCardDto.setDiscountPercentage(discountCard.getDiscount());
        return discountCardDto;
    }
}

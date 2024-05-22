package org.example.tapville.helpers.mappers;

import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.DiscountCard;
import org.example.tapville.models.dtos.DiscountCardDto;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardMapper {
    public DiscountCardMapper() {
    }

    public DiscountCard createDiscountCard(DiscountCardDto cardDto, Business business, Customer customer) {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setCreator(business);
        discountCard.setOwner(customer);
        discountCard.setDiscount(cardDto.getDiscountPercentage());
        return discountCard;
    }

    public DiscountCardDto discountCardToDto(DiscountCard discountCard) {
        DiscountCardDto discountCardDto = new DiscountCardDto();
        discountCardDto.setDiscountPercentage(discountCard.getDiscount());
        return discountCardDto;
    }
}

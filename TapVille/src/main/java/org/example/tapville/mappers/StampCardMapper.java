package org.example.tapville.mappers;

import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.StampCard;
import org.example.tapville.models.dtos.StampCardDto;
import org.springframework.stereotype.Component;

@Component
public class StampCardMapper {

    public StampCardMapper() {
    }

    public StampCard createStampCard(StampCardDto cardDto, Business business, Customer customer) {
        StampCard stampCard = new StampCard();
        stampCard.setCreator(business);
        stampCard.setOwner(customer);
        stampCard.setStamps(cardDto.getNumberOfStamps());
        return stampCard;
    }

    public StampCardDto stampCardToDto(StampCard stampCard) {
        StampCardDto stampCardDto = new StampCardDto();
        stampCardDto.setNumberOfStamps(stampCard.getStamps());
        return stampCardDto;
    }
}

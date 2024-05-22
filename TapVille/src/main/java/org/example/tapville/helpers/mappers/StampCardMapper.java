//package org.example.tapville.helpers.mappers;
//
//import org.example.tapville.models.Business;
//import org.example.tapville.models.Card;
//import org.example.tapville.models.Customer;
//import org.example.tapville.models.StampCard;
//import org.example.tapville.models.dtos.CardDto;
//import org.example.tapville.models.dtos.StampCardDto;
//import org.example.tapville.services.contracts.CardService;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StampCardMapper extends CardMapper {
//
//    public StampCardMapper(CardService cardService) {
//        super(cardService);
//    }
//    public StampCard createStampCard (StampCardDto cardDto,Business business,Customer customer){
//        StampCard stampCard = new StampCard();
//        stampCard.setCreator(business);
//        stampCard.setOwner(customer);
//        stampCard.setCardName(cardDto.getCardName());
//        stampCard.setStamps(cardDto.getNumberOfStamps());
//        return stampCard;
//    }
//
//    public StampCardDto stampCardToDto(StampCard stampCard){
//        StampCardDto stampCardDto = new StampCardDto();
//        stampCardDto.setCardName(stampCard.getCardName());
//        stampCardDto.setNumberOfStamps(stampCard.getStamps());
//        return stampCardDto;
//    }
//
//}

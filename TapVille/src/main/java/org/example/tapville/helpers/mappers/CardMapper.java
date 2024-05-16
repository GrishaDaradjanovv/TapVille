package org.example.tapville.helpers.mappers;

import org.example.tapville.models.Business;
import org.example.tapville.models.Card;
import org.example.tapville.models.Customer;
import org.example.tapville.models.dtos.CardDto;
import org.example.tapville.services.contracts.CardService;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    private final CardService cardService;

    public CardMapper(CardService cardService) {
        this.cardService = cardService;
    }

    public Card createCard(CardDto cardDto, Business business, Customer customer){
        Card card = new Card();
        card.setCreator(business);
        card.setOwner(customer);
        card.setCardName(cardDto.getCardName());
        return card;
    }

    public CardDto cardToDto(Card card){
        CardDto cardDto = new CardDto();
        cardDto.setCardName(card.getCardName());
        return cardDto;
    }
}

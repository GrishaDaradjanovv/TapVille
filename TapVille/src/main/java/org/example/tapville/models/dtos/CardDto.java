package org.example.tapville.models.dtos;

import jakarta.validation.constraints.Size;

public class CardDto {
    @Size(min = 3, message = "Name must be minimum 3 symbols")
    private String cardName;


    public CardDto() {
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
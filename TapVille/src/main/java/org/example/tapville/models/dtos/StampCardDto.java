package org.example.tapville.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class StampCardDto {
    private String cardName;
    private int numberOfStamps;

    public StampCardDto() {
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getNumberOfStamps() {
        return numberOfStamps;
    }

    public void setNumberOfStamps(int numberOfStamps) {
        this.numberOfStamps = numberOfStamps;
    }
}

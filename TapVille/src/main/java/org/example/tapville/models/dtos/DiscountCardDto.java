package org.example.tapville.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Percentage;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class DiscountCardDto {
    @Percentage
    @NotNull
    private double discountPercentage;

    public DiscountCardDto() {
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}

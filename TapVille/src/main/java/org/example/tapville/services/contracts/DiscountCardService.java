package org.example.tapville.services.contracts;

import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.DiscountCard;

import java.util.List;

public interface DiscountCardService {
    DiscountCard createDiscountCard(DiscountCard card, double percentage, Business creator, Customer owner);

    List<DiscountCard> getDiscountCardsByOwner(Customer owner);

    List<DiscountCard>getAll();

    DiscountCard update(DiscountCard discountCard, Business creator);

    DiscountCard changeDiscount(DiscountCard card, Business creator, double percentage);

}

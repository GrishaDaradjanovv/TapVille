package org.example.tapville.services.contracts;

import com.google.zxing.WriterException;
import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.DiscountCard;

import java.io.IOException;
import java.util.List;

public interface DiscountCardService {
    void createDiscountCard(DiscountCard card, double percentage, Business creator, Customer owner) throws IOException, WriterException;

    List<DiscountCard> getDiscountCardsByOwner(Customer owner);

    List<DiscountCard>getAll();

    DiscountCard update(DiscountCard discountCard, Business creator);

    DiscountCard changeDiscount(DiscountCard card, Business creator, double percentage);
    DiscountCard getById(Long id);
}

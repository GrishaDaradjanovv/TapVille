package org.example.tapville.services.contracts;

import org.example.tapville.models.*;

import java.util.List;

public interface CardService {
    Card getById(Long id);

    Card create(Card card, Business creator, Customer owner);

    StampCard createStampCard(StampCard card,Business creator, Customer owner);

    DiscountCard createDiscountCard(DiscountCard card,Business creator, Customer owner);

    Card update(Card card, Customer admin);

    void deleteById(Long id,Customer admin);

    List<Card> getAll();

    List<Card> findCardsByCreator(Business creator);

    List<Card> findCardsByOwner(Customer owner);

    List<StampCard> getStampCardsByOwner(Customer owner);

    List<DiscountCard> getDiscountCardsByOwner(Customer owner);
}

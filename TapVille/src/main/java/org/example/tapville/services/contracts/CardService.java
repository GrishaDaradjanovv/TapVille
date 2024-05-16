package org.example.tapville.services.contracts;

import org.example.tapville.models.*;

import java.util.List;

public interface CardService {
    Card getById(Long id);

    Card create(Card card);

    StampCard createStampCard(StampCard card);

    DiscountCard createDiscountCard(DiscountCard card);

    Card update(Card card);

    void deleteById(Long id);

    List<Card> getAll();

    List<Card> findCardsByCreator(Business creator);

    List<Card> findCardsByOwner(Customer owner);

    List<StampCard> getStampCardsByOwner(Customer owner);

    List<DiscountCard> getDiscountCardsByOwner(Customer owner);
}

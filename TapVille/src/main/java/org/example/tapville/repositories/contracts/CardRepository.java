package org.example.tapville.repositories.contracts;

import org.example.tapville.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card getCardById(Long id);

    List<Card> findCardsByCreator(Business creator);

    List<Card> findCardsByOwner(Customer owner);

    @Query("SELECT c FROM Card c WHERE TYPE(c) = StampCard AND c.owner = :owner")
    List<StampCard> getStampCardsByOwner(@Param("owner") Customer owner);

    @Query("SELECT c FROM Card c WHERE TYPE(c) = DiscountCard AND c.owner = :owner")
    List<DiscountCard>getDiscountCardsByOwner(@Param("owner")Customer owner);

    @Query("SELECT c FROM Card c WHERE TYPE(c) = StampCard")
    List<StampCard> findAllStampCards();

    @Query("SELECT c FROM Card c WHERE TYPE(c) = DiscountCard")
    List<DiscountCard> findAllDiscountCards();
}

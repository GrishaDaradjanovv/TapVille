package org.example.tapville.repositories.contracts;

import org.example.tapville.models.Customer;
import org.example.tapville.models.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscountCardRepository extends JpaRepository<DiscountCard, Long> {
    @Query("select d from DiscountCard d")
    List<DiscountCard> getAll();

    @Query("SELECT d FROM DiscountCard d WHERE d.owner = :owner")
    List<DiscountCard> getDiscountCardsByOwner(@Param("owner") Customer owner);

    @Query("SELECT d FROM DiscountCard d WHERE d.id = :id")
    DiscountCard getDiscountCardById(@Param("id") Long id);
}

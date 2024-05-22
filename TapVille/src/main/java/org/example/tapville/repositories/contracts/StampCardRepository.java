package org.example.tapville.repositories.contracts;
import org.example.tapville.models.Customer;
import org.example.tapville.models.DiscountCard;
import org.example.tapville.models.StampCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StampCardRepository extends JpaRepository<StampCard, Long> {
    @Query("select d from StampCard d")
    List<StampCard>getAll();
    @Query("SELECT s FROM StampCard s WHERE s.owner = :owner")
    List<StampCard> getStampCardsByOwner(@Param("owner") Customer owner);
    StampCard getStampCardById(Long id);

}

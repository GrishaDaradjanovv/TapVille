package org.example.tapville.services.contracts;

import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.StampCard;

import java.util.List;

public interface StampCardService {
    void createStampCard(StampCard card, Business creator, Customer owner) throws Exception;

    StampCard getById(Long id);

    List<StampCard>getAll();

    List<StampCard> getStampCardsByOwner(Customer owner);

    StampCard addStamp(StampCard stampCard, Business creator);

    StampCard removeStamp(StampCard stampCard, Business creator);

    StampCard update(StampCard stampCard, Business creator);

}

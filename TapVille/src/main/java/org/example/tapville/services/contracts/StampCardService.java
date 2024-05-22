package org.example.tapville.services.contracts;

import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.StampCard;

import java.util.List;

public interface StampCardService {
    StampCard createStampCard(StampCard card, Business creator, Customer owner);

    List<StampCard>getAll();

    List<StampCard> getStampCardsByOwner(Customer owner);

    StampCard addStamp(StampCard stampCard, Business creator, int stamp);

    StampCard removeStamp(StampCard stampCard, Business creator, int stamp);

    StampCard update(StampCard stampCard, Business creator);

}

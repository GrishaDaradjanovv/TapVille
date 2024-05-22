package org.example.tapville.services;

import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.exceptions.InvalidOperationException;
import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.StampCard;
import org.example.tapville.repositories.contracts.StampCardRepository;
import org.example.tapville.services.contracts.StampCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StampCardServiceImpl implements StampCardService {
    private static final String CANNOT_PERFORM_OPERATION = "You can't perform this operation";
    private final StampCardRepository stampCardRepository;

    @Autowired
    public StampCardServiceImpl(StampCardRepository stampCardRepository) {
        this.stampCardRepository = stampCardRepository;
    }


    @Override
    public StampCard createStampCard(StampCard card, Business creator, Customer owner) {
        if (creator == null && owner == null) {
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
        card.setCreator(creator);
        card.setOwner(owner);
        return stampCardRepository.save(card);
    }

    @Override
    public List<StampCard> getAll() {
        return stampCardRepository.getAll();
    }

    @Override
    public List<StampCard> getStampCardsByOwner(Customer owner) {
        return stampCardRepository.getStampCardsByOwner(owner);
    }

    @Override
    public StampCard update(StampCard card, Business creator) {
        Optional<StampCard> cardOptional = stampCardRepository.findById(card.getId());
        if (cardOptional.isPresent()) {
            return stampCardRepository.save(card);
        } else {
            throw new EntityNotFoundException("Card", "ID", String.valueOf(card.getId()));
        }
    }


    @Override
    public StampCard addStamp(StampCard stampCard, Business creator, int stamp) {
        checkCreator(creator, stampCard);
        StampCard card = stampCardRepository.getStampCardById(stampCard.getId());
        if (card != null) {
            stampCard.setStamps(stampCard.getStamps() + stamp);
            update(card, creator);
            return stampCard;
        } else {
            throw new EntityNotFoundException("Card", "ID", String.valueOf(stampCard.getId()));

        }
    }

    @Override
    public StampCard removeStamp(StampCard stampCard, Business creator, int stamp) {
        checkCreator(creator, stampCard);
        StampCard card = stampCardRepository.getStampCardById(stampCard.getId());
        if (card != null) {
            stampCard.setStamps(stampCard.getStamps() - stamp);
            update(card, creator);
            return stampCard;
        } else {
            throw new EntityNotFoundException("Card", "ID", String.valueOf(stampCard.getId()));

        }
    }

    private boolean checkCreator(Business creator, StampCard card) {
        if (creator != null || card.getCreator() != null || creator.equals(card.getCreator().getId())) {
            return true;
        } else {
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
    }
}

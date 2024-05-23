package org.example.tapville.services;

import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.exceptions.InvalidOperationException;
import org.example.tapville.models.Business;
import org.example.tapville.models.Customer;
import org.example.tapville.models.DiscountCard;
import org.example.tapville.repositories.contracts.DiscountCardRepository;
import org.example.tapville.services.contracts.DiscountCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountCardServiceImpl implements DiscountCardService {
    private static final String CANNOT_PERFORM_OPERATION = "You can't perform this operation";

    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public DiscountCardServiceImpl(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    @Override
    public DiscountCard createDiscountCard(DiscountCard card, double percentage, Business creator, Customer owner) {
        if (creator == null && owner == null) {
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
        card.setCreator(creator);
        card.setOwner(owner);
        card.setDiscount(percentage);
        card.setCreationDate(new Timestamp(System.currentTimeMillis()));
        return discountCardRepository.save(card);
    }

    @Override
    public DiscountCard update(DiscountCard card, Business creator) {
        Optional<DiscountCard> cardOptional = discountCardRepository.findById(card.getId());
        if (cardOptional.isPresent()) {
            return discountCardRepository.save(card);
        } else {
            throw new EntityNotFoundException("Card", "ID", String.valueOf(card.getId()));
        }
    }

    @Override
    public List<DiscountCard> getDiscountCardsByOwner(Customer owner) {
        return discountCardRepository.getDiscountCardsByOwner(owner);
    }

    @Override
    public List<DiscountCard> getAll() {
        return discountCardRepository.getAll();
    }

    @Override
    public DiscountCard changeDiscount(DiscountCard card, Business creator, double percentage) {
        checkCreator(creator, card);
        DiscountCard discountCard = discountCardRepository.getDiscountCardById(card.getId());
        if (discountCard != null) {
            discountCard.setDiscount(percentage);
            update(discountCard, creator);
            return discountCard;
        } else {
            throw new EntityNotFoundException("Card", "ID", String.valueOf(card.getId()));

        }
    }

    private boolean checkCreator(Business creator, DiscountCard card) {
        if (creator != null || card.getCreator() != null || creator.equals(card.getCreator().getId())) {
            return true;
        } else {
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
    }
}

package org.example.tapville.services;


import org.example.tapville.exceptions.EntityNotFoundException;
import org.example.tapville.exceptions.InvalidOperationException;
import org.example.tapville.models.*;
import org.example.tapville.repositories.contracts.CardRepository;
import org.example.tapville.services.contracts.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private static final String CANNOT_PERFORM_OPERATION = "You can't perform this operation";
    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card getById(Long id) {
        Card card = cardRepository.getCardById(id);
        if (card != null) {
            return card;
        } else {
            throw new EntityNotFoundException("Card", "ID", String.valueOf(id));
        }
    }

    @Override
    public Card create(Card card,Business creator,Customer owner) {
        if (creator == null && owner == null){
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
        card.setCreator(creator);
        card.setOwner(owner);
        return cardRepository.save(card);
    }


    @Override
    public StampCard createStampCard(StampCard card,Business creator, Customer owner) {
        if (creator == null && owner == null){
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
        card.setCreator(creator);
        card.setOwner(owner);
        return cardRepository.save(card);
    }

    @Override
    public DiscountCard createDiscountCard(DiscountCard card,Business creator, Customer owner) {
        if (creator == null && owner == null){
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
        card.setCreator(creator);
        card.setOwner(owner);
        return cardRepository.save(card);
    }

    @Override
    public Card update(Card card,Customer admin) {
        if (!admin.isAdmin()){
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
        Optional<Card> cardOptional = cardRepository.findById(card.getId());
        if (cardOptional.isPresent()) {
            return cardRepository.save(card);
        }
        throw new EntityNotFoundException("Card", "ID", String.valueOf(card.getId()));
    }

    @Override
    public void deleteById(Long id,Customer admin) {
        if (!admin.isAdmin()){
            throw new InvalidOperationException(CANNOT_PERFORM_OPERATION);
        }
        Card card = getById(id);
        card.setDeleted(true);
        cardRepository.save(card);
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @Override
    public List<Card> findCardsByCreator(Business creator) {
        return cardRepository.findCardsByCreator(creator);
    }

    @Override
    public List<Card> findCardsByOwner(Customer owner) {
        return cardRepository.findCardsByOwner(owner);
    }

    @Override
    public List<StampCard> getStampCardsByOwner(Customer owner) {
        return cardRepository.getStampCardsByOwner(owner);
    }

    @Override
    public List<DiscountCard> getDiscountCardsByOwner(Customer owner) {
        return cardRepository.getDiscountCardsByOwner(owner);
    }

}

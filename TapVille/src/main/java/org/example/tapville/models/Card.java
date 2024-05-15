package org.example.tapville.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "cards")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("DTYPE")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int id;

    @Column(name = "card_name")
    private String cardName;

//    @OneToOne(mappedBy = "type", cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "customer_id")

    private Customer owner;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "business_id")
    @JsonBackReference
    private Business creator;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public Business getCreator() {
        return creator;
    }

    public void setCreator(Business creator) {
        this.creator = creator;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

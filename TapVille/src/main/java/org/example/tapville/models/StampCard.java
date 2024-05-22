package org.example.tapville.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Stamp")
@Table(name = "stamp_cards")
public class StampCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_card_id")
    long id;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "customer_id")
    private Customer owner;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "business_id")
    @JsonBackReference
    private Business creator;
    @Column(name = "stamps")
    private int stamps;

    public StampCard() {
    }

    public int getStamps() {
        return stamps;
    }

    public long getId() {
        return id;
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

    public void setStamps(int stamps) {
        this.stamps = stamps;
    }
}

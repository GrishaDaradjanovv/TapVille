package org.example.tapville.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "discount_cards")
@DiscriminatorValue("discount")
public class DiscountCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_card_id")
    long id;
    @Column(name = "discount_percentage")
    private double discount;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "customer_id")
    private Customer owner;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "business_id")
    @JsonBackReference
    private Business creator;
    @Column(name = "creation_date")
    private Timestamp creationDate;

    public DiscountCard() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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
}

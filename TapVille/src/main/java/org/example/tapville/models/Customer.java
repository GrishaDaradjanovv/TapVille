package org.example.tapville.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customers_stamp_cards",
            joinColumns = @JoinColumn(name = "customer"),
            inverseJoinColumns = @JoinColumn(name = "stamp_card")
    )
    private Set<StampCard>stampCards;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customers_discount_cards",
            joinColumns = @JoinColumn(name = "customer"),
            inverseJoinColumns = @JoinColumn(name = "discount_card")
    )
    private Set<DiscountCard>discountCards;
    public Customer() {
    }

    public Set<StampCard> getStampCards() {
        return stampCards;
    }

    public void setStampCards(Set<StampCard> stampCards) {
        this.stampCards = stampCards;
    }

    public Set<DiscountCard> getDiscountCards() {
        return discountCards;
    }

    public void setDiscountCards(Set<DiscountCard> discountCards) {
        this.discountCards = discountCards;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

package org.example.tapville.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    @JsonIgnore
    private long id;

    @Column(name = "busines_name")
    private String businessName;

    @Column(name = "busines_code")
    @JsonIgnore
    private String businessCode;

    @Column(name = "is_active")
    @JsonIgnore
    private boolean isActive;

    @Column(name = "is_deleted")
    @JsonIgnore
    private boolean isDeleted;
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DiscountCard> discountCards;
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<StampCard> stampCards;

    public Business() {
    }

    public long getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public Set<DiscountCard> getDiscountCards() {
        return discountCards;
    }

    public void setDiscountCards(Set<DiscountCard> discountCards) {
        this.discountCards = discountCards;
    }

    public Set<StampCard> getStampCards() {
        return stampCards;
    }

    public void setStampCards(Set<StampCard> stampCards) {
        this.stampCards = stampCards;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Business business = (Business) object;
        return id == business.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

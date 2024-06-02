package org.example.tapville.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@DiscriminatorValue("Stamp")
@Table(name = "stamp_cards")
public class StampCard{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_card_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "customer_id")
    private Customer owner;
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "business_id")
    @JsonBackReference
    private Business creator;
    @Column(name = "stamps")
    private int stamps;
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "qr_code_path")
    private String path;

    @Lob
    @Column(name = "qr_code_img")
    private byte [] qrCodeImage;


    public StampCard() {
    }


    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public int getStamps() {
        return stamps;
    }

    public Long getId() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(byte[] qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }
}

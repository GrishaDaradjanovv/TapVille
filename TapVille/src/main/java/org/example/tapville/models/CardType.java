package org.example.tapville.models;

import jakarta.persistence.*;

@Entity
@Table(name = "card_type")
public class CardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

}

package ru.cft.yellowrubberduck.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@Entity
public class Card {
    UUID id;
    String image;
    String description;
    Double price;
    UUID ownerID;
    boolean isRent;
    UUID customerID;
    String date;
    String category;

}

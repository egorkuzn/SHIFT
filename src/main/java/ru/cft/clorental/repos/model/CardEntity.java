package ru.cft.clorental.repos.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Card")

public class CardEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "image", nullable = false)

    public String image;
    @Column(name = "description", nullable = false)

    public String description;
    @Column(name = "price", nullable = false)

    public Double price;
    @Column(name = "ownerID", nullable = false)

    public Long ownerID;
    @Column(name = "rentStatus", nullable = false)

    public boolean isRent;
    @Column(name = "customerID", nullable = false)

    public Long customerId;
    @Column(name = "dateWhenItFinish", nullable = false)

    public Date term;
    @Column(name = "category", nullable = false)

    public String category;
}

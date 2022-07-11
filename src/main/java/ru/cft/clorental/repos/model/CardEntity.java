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

    @Column(name = "image")

    public String image;
    @Column(name = "description")

    public String description;
    @Column(name = "price")

    public Double price;
    @Column(name = "ownerID")

    public Long ownerID;
    @Column(name = "rentStatus")

    public boolean isRent;
    @Column(name = "customerID")

    public Long customerId;
    @Column(name = "dateWhenItFinish")

    public Date term;
    @Column(name = "category")

    public String category;
}

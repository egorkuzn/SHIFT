package ru.cft.clorental.repos.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Card")

public class CardEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long id;

    @OneToMany
    @JoinColumn(name = "images")
    public Set<ImageEntity> images = new HashSet<>();
    @Column(name = "description")

    public String description;
    @Column(name = "price")

    public Double price;
    @Column(name = "ownerID")

    public Long ownerID;
    @Column(name = "rentStatus")

    public Boolean rent;
    @Column(name = "customerID")

    public Long customerId;
    @Column(name = "dateOfFinish")
    public Date term;
    @Column(name = "dateWhenItStart")
    public Date startDate;
    @Column(name = "dateWhenItFinished")
    public Date finishDate;
    @Column(name = "markFromOwner")
    public Boolean markFromOwner;
    @Column(name = "markFromCustomer")
    public Boolean markFromCustomer;
    @Column(name = "category")

    public String category;
    @Column(name = "title")
    public String title;
}

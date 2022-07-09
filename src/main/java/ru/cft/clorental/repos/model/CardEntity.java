package ru.cft.clorental.repos.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Card")

public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "image", nullable = false)

    public String image;
    @Column(name = "description", nullable = false)

    public String description;
    @Column(name = "price", nullable = false)

    public Double price;
    @Column(name = "owner ID", nullable = false)

    public Long ownerID;
    @Column(name = "rent status", nullable = false)

    public boolean isRent;
    @Column(name = "customer ID", nullable = false)

    public Long custormerId;
    @Column(name = "date when it finish", nullable = false)

    public Date term;
    @Column(name = "category", nullable = false)

    public String category;
}

package ru.cft.clorental.repos.model;


import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Discussion")
public class DiscussionEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = AUTO)
    public Long id;
    @Column(name = "clientID")
    public Long clientID;
    @Column(name = "message")
    public String message;
}

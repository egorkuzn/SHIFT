package ru.cft.clorental.repos.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Messages")
public class MessageEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = AUTO)
    public Long id;
    @Column(name = "message")
    public String message;
}

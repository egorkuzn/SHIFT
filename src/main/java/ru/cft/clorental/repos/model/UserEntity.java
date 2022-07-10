package ru.cft.clorental.repos.model;

import javax.persistence.*;

import java.util.Set;
import java.util.TreeSet;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "UserEntity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    public Long id;
    @Column(name = "hash", nullable = false)
    public String hash;
    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "surname", nullable = false)
    public String surname;
    @Column(name = "email", nullable = false)
    public String email;
    @Column(name = "phone", nullable = false)

    public String phone;
    @OneToMany
    @JoinColumn(name = "favouriteCards", nullable = false)
    public Set<CardEntity> liked;

    @OneToMany
    @JoinColumn(name = "ownedCards", nullable = false)
    public Set<CardEntity> own;

    @OneToMany
    @JoinColumn(name = "rentCards", nullable = false)
    public Set<CardEntity> rent;
}

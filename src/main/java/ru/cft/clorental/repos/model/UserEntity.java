package ru.cft.clorental.repos.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "UserEntity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long id;
    @Column(name = "hash")
    public String hash;
    @Column(name = "name")
    public String name;
    @Column(name = "surname")
    public String surname;
    @Column(name = "email")
    public String email;

    @Column(name = "phone")
    public String phone;

    @OneToOne
    @JoinColumn(name = "personalIcon")
    public ImageEntity personalIcon;

    @Column(name = "isVerified")
    public Boolean verified;
    @Column(name = "maxRentCount")
    public Integer maxRentCount = 10;
    @Column(name = "maxOwnCount")
    public Integer maxOwnCount = 10;
    @Column(name = "emailCode")
    public String emailCode;
    @OneToMany
    @JoinColumn(name = "likedCards")
    public Set<CardEntity> liked = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "ownCards")
    public Set<CardEntity> own = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "rentCards")
    public Set<CardEntity> rent = new HashSet<>();
    @OneToMany
    @JoinColumn(name = "discussion")
    public Set<DiscussionEntity> discussion;

}

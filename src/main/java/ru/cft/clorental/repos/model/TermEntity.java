package ru.cft.clorental.repos.model;

import javax.persistence.*;

@Entity
@Table(name = "term table")
public class TermEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @ManyToOne
    public UserEntity userEntity;
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String emailCode;
}

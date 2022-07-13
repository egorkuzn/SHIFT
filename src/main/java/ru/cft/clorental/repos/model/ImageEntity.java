package ru.cft.clorental.repos.model;

import javax.persistence.*;
import java.io.File;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Images")
public class ImageEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = AUTO)
    public Long id;
    @Column(name = "imageURL")
    public String imageURL;
    @Column(name = "imageFile")
    public byte[] imageFile;
}

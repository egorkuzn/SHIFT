package ru.cft.clorental.model.request_forms;


import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.repos.model.ImageEntity;
import ru.cft.clorental.repos.model.UserEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CardMessage {
    public CardMessage(CardEntity firstById, UserEntity owner, UserEntity customer){
        this.id = firstById.id;
        this.title = firstById.title;
        this.description = firstById.description;
        this.price = firstById.price;
        this.ownerName = owner.name;
        this.ownerSurname = owner.surname;
        this.ownerPhone = owner.phone;

        if(customer != null) {
            this.customerName = customer.name;
            this.customerSurname = customer.surname;
            this.customerPhone = customer.phone;
        }

        this.rent = firstById.rent;
        this.term = firstById.term;
        this.startDate = firstById.startDate;
        this.finishDate = firstById.finishDate;
        this.category = firstById.category;
        this.imagesURL = new ArrayList<>();

        for(ImageEntity elem : firstById.images)
            this.imagesURL.add(elem.imageURL);
    }

    public Long id;
    public String title;
    public List<String> imagesURL;
    public String description;
    public Double price;
    public String ownerName;
    public String ownerSurname;
    public String ownerPhone;
    public Boolean rent;
    public String customerName;
    public String customerSurname;
    public String customerPhone;
    public Date term;
    public Date startDate;
    public Date finishDate;
    public String category;
}

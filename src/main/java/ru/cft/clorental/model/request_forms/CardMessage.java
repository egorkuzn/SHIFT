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

    private final Long id;
    private final List<String> imagesURL;
    private final String description;
    private final Double price;
    private final String ownerName;
    private final String ownerSurname;
    private final String ownerPhone;
    private final Boolean rent;
    private String customerName;
    private String customerSurname;
    private String customerPhone;
    private final Date term;
    private final Date startDate;
    private final Date finishDate;
    private final String category;
}

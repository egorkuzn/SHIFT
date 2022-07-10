package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.cft.clorental.model.CardChangeCommand;
import ru.cft.clorental.model.NewCardForm;
import ru.cft.clorental.model.RequestForGettingCardsOfOneType;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CardsService {
    protected final CardsRepo cardsRepo;
    protected final UsersRepo usersRepo;

    @Autowired
    public CardsService(CardsRepo cardsRepo, UsersRepo usersRepo) {
        this.cardsRepo = cardsRepo;
        this.usersRepo = usersRepo;
    }

    protected CardEntity generatedNewCard(NewCardForm form) {
        CardEntity cardEntity = new CardEntity();

        cardEntity.customerId = form.userID;
        cardEntity.category = form.category;
        cardEntity.description = form.description;
        cardEntity.price = form.price;
        cardEntity.term = form.term;
        cardEntity.image = form.imageURL;
        cardEntity.isRent = false;

        return cardEntity;
    }

    public List<Long> getCards(RequestForGettingCardsOfOneType request) {
        Collection<CardEntity> cardsCollection = typeCollection(request);

        if(cardsCollection != null) {
            CardEntity[] cards = (CardEntity[]) cardsCollection.stream().toArray();
            ArrayList<Long> idList = new ArrayList<>();

            for(CardEntity elem: cards)
                idList.add(elem.id);

            return idList;
        }

        return null;
    }

    public boolean addNewCard(NewCardForm form) {
        CardEntity cardEntity = generatedNewCard(form);

        cardsRepo.save(cardEntity);
        addInTypeNewCard(form, cardEntity);

        return true;
    }

    protected abstract Collection<CardEntity> typeCollection(RequestForGettingCardsOfOneType request);

    protected abstract void addInTypeNewCard(NewCardForm form, CardEntity cardEntity);

    public abstract boolean makeChanges(CardChangeCommand command);
}

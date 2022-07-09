package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.CardChangeCommand;
import ru.cft.clorental.model.NewCardForm;
import ru.cft.clorental.model.RequestForGettingCardsOfOneType;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.model.CardEntity;

import java.sql.Date;
import java.util.List;

@Service
public class UserCardsService {
    final CardsRepo cardsRepo;

    @Autowired
    public UserCardsService(CardsRepo cardsRepo) {
        this.cardsRepo = cardsRepo;
    }


    public List<Long> getCards(RequestForGettingCardsOfOneType request) {
        return cardsRepo.findAllByOwnerIDAndCategory(request.userID, request.type);
    }

    public boolean addNewCard(NewCardForm form) {
        CardEntity cardEntity = generatedNewCard(form);
        cardsRepo.save(cardEntity);
        return true;
    }

    private CardEntity generatedNewCard(NewCardForm form) {
        CardEntity cardEntity = new CardEntity();

        cardEntity.custormerId = form.userID;
        cardEntity.category = form.category;
        cardEntity.description = form.description;
        cardEntity.price = form.price;
        cardEntity.term = form.term;
        cardEntity.image = form.imageURL;
        cardEntity.isRent = false;

        return cardEntity;
    }

    public boolean makeChanges(CardChangeCommand command) {
        CardEntity card = cardsRepo.findFirstByOwnerIDAndId(command.userID, command.cardID);

        switch (command.what) {
            case "term":
                card.term = Date.valueOf(command.onWhat);
                break;
            case "price":
                card.price = Double.parseDouble(command.onWhat);
                break;
            case "image":
                card.image = command.onWhat;
                break;
            case "rentStatus":
                card.isRent = Boolean.getBoolean(command.onWhat);
                break;
            case "category":
                card.category = command.onWhat;
                break;
            case "":
                card.custormerId
            default:
                break;
        }

        return true;
    }
}

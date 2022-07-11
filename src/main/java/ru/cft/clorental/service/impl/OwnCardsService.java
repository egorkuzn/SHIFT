package ru.cft.clorental.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.UserIDCardID;
import ru.cft.clorental.model.request_forms.CardChangeCommand;
import ru.cft.clorental.model.request_forms.NewCardForm;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.MeCardsService;

import java.sql.Date;
import java.util.Set;

@Service
@Slf4j
public class OwnCardsService extends MeCardsService {
    public OwnCardsService(CardsRepo cardsRepo, UsersRepo usersRepo) {
        super(cardsRepo, usersRepo);
    }

    @Override
    public boolean delete(UserIDCardID command) {
        usersRepo.findFirstById(command.userID).own.remove(cardsRepo.findFirstById(command.cardID));
        cardsRepo.deleteById(command.cardID);
        return true;
    }

    @Override
    protected Set<CardEntity> returnTypeSet(Long userID) {
        return usersRepo.findFirstById(userID).own;
    }

    public boolean makeChanges(CardChangeCommand command) {
        CardEntity card = cardsRepo.findFirstByOwnerIDAndId(command.userID, command.cardID);

        switch (command.what) {
            case "term" -> card.term = Date.valueOf(command.onWhat);
            case "price" -> card.price = Double.parseDouble(command.onWhat);
            case "imageURL" -> card.image = command.onWhat;
            case "rentStatus" -> card.isRent = Boolean.getBoolean(command.onWhat);
            case "category" -> card.category = command.onWhat;
            case "customerID" -> card.customerId = Long.parseLong(command.onWhat);
            case "ownerID" -> card.ownerID = Long.parseLong(command.onWhat);
            case "description" -> card.description = command.onWhat;
            default -> {}
        }

        return true;
    }

    public boolean addNewCard(NewCardForm form){
        CardEntity card = generatedNewCard(form);
        cardsRepo.save(card);
        usersRepo.findFirstById(form.userID).own.add(card);
        usersRepo.flush();
        return true;
    }

    protected CardEntity generatedNewCard(NewCardForm form) {
        CardEntity cardEntity = new CardEntity();

        cardEntity.ownerID = form.userID;
        cardEntity.category = form.category;
        cardEntity.description = form.description;
        cardEntity.price = form.price;
        cardEntity.term = form.term;
        cardEntity.image = form.imageURL;
        cardEntity.isRent = false;

        return cardEntity;
    }
}

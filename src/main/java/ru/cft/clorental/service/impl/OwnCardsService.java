package ru.cft.clorental.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.clorental.model.request_forms.CardChangeCommand;
import ru.cft.clorental.model.request_forms.NewCardForm;
import ru.cft.clorental.model.request_forms.RequestForGettingCardsOfOneType;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.MeCardsService;

import java.sql.Date;
import java.util.Collection;

@Service
public class OwnCardsService extends MeCardsService {
    public OwnCardsService(CardsRepo cardsRepo, UsersRepo usersRepo) {
        super(cardsRepo, usersRepo);
    }

    @Override
    protected Collection<CardEntity> typeCollection(RequestForGettingCardsOfOneType request){
        return usersRepo.findFirstById(request.userID).own;
    }

    @Override
    protected void addInTypeNewCard(NewCardForm form, CardEntity cardEntity) {
        usersRepo.findFirstById(form.userID).own.add(cardEntity);
    }

    @Override
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
}

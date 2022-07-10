package ru.cft.clorental.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.clorental.model.CardChangeCommand;
import ru.cft.clorental.model.NewCardForm;
import ru.cft.clorental.model.RequestForGettingCardsOfOneType;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.CardsService;
import java.util.Collection;

@Service
public class RentCardsService extends CardsService {
    public RentCardsService(CardsRepo cardsRepo, UsersRepo usersRepo) {
        super(cardsRepo, usersRepo);
    }

    @Override
    protected Collection<CardEntity> typeCollection(RequestForGettingCardsOfOneType request){
        return usersRepo.findFirstById(request.userID).rent;
    }

    @Override
    protected void addInTypeNewCard(NewCardForm form, CardEntity cardEntity) {
        usersRepo.findFirstById(form.userID).rent.add(cardEntity);
    }

    @Override
    public boolean makeChanges(CardChangeCommand command) {
        return true;
    }
}

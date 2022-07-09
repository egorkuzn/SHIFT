package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.CardChangeCommand;
import ru.cft.clorental.model.RequestForGettingCardsOfOneType;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.model.CardEntity;

import java.util.List;

@Service
public class UserCardsService {
    final CardsRepo cardsRepo;

    @Autowired
    public UserCardsService(CardsRepo cardsRepo) {
        this.cardsRepo = cardsRepo;
    }


    public List<Long> getCards(RequestForGettingCardsOfOneType request) {
        cardsRepo.findAllByOAndOwnerIDAndAndCategory(request.id, request.type);
    }

    public boolean addNewCard(CardEntity newCard) {

    }

    publiс public boolean makeChanges(CardChangeCommand command){

    }
}

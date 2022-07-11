package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.cft.clorental.model.UserIDCardID;
import ru.cft.clorental.model.request_forms.RequestForGettingCardsOfOneType;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class MeCardsService {
    protected final CardsRepo cardsRepo;
    protected final UsersRepo usersRepo;

    @Autowired
    public MeCardsService(CardsRepo cardsRepo, UsersRepo usersRepo) {
        this.cardsRepo = cardsRepo;
        this.usersRepo = usersRepo;
    }

    public List<Long> getCards(RequestForGettingCardsOfOneType request) {
        Set<CardEntity> cardEntities = returnTypeSet(request.userID);
        List<Long> list = new ArrayList<>();
        cardEntities.forEach(elem -> list.add(elem.id));
        return list;
    }

    public abstract boolean delete(UserIDCardID command);

    protected abstract Set<CardEntity> returnTypeSet(Long userID);
}

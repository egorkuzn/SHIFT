package ru.cft.clorental.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.clorental.model.UserIDCardID;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.MeCardsService;
import java.util.*;

@Service
public class LikedCardsService extends MeCardsService {
    public LikedCardsService(CardsRepo cardsRepo, UsersRepo usersRepo) {
        super(cardsRepo, usersRepo);
    }

    protected Set<CardEntity> returnTypeSet(Long userID){
        return usersRepo.findFirstById(userID).liked;
    }


    public boolean addNewCard(UserIDCardID command){
        usersRepo.findFirstById(command.userID).liked.add(cardsRepo.findFirstById(command.cardID));
        return true;
    }
    public boolean delete(UserIDCardID command) {
        usersRepo.findFirstById(command.userID).liked.remove(cardsRepo.findFirstById(command.cardID));
        return true;
    }
}

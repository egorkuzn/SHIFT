package ru.cft.clorental.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.clorental.model.UserIDCardID;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.MeCardsService;
import java.util.Set;

@Service
public class RentCardsService extends MeCardsService {
    public RentCardsService(CardsRepo cardsRepo, UsersRepo usersRepo) {
        super(cardsRepo, usersRepo);
    }

    protected Set<CardEntity> returnTypeSet(Long userID){
        return usersRepo.findFirstById(userID).rent;
    }


    public boolean addNewCard(UserIDCardID command){
        if(!cardsRepo.findFirstByIdAndRent(command.cardID,false).rent) {
            usersRepo.findFirstById(command.userID).rent.add(cardsRepo.findFirstById(command.cardID));
            usersRepo.flush();
            cardsRepo.findFirstById(command.cardID).rent = true;
            cardsRepo.findFirstById(command.cardID).customerId = command.userID;
        }

        return true;
    }
    public boolean delete(UserIDCardID command) {
        usersRepo.findFirstById(command.userID).rent.remove(cardsRepo.findFirstById(command.cardID));
        cardsRepo.findFirstById(command.cardID).rent = false;
        return true;
    }
}

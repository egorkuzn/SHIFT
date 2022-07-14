package ru.cft.clorental.service.impl;

import org.springframework.stereotype.Service;
import ru.cft.clorental.model.request_forms.UserIDCardID;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.repos.model.UserEntity;
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
        CardEntity card = cardsRepo.findFirstByIdAndRent(command.cardID,false);
        UserEntity user = usersRepo.findFirstById(command.userID);

        if(user.rent.size() == user.maxRentCount)
            return false;

        card.markFromCustomer = true;
        card.customerId = command.userID;

        user.rent.add(card);
        usersRepo.flush();
        cardsRepo.flush();
        return true;
    }
    public boolean delete(UserIDCardID command) {
        usersRepo.findFirstById(command.userID).rent.remove(cardsRepo.findFirstById(command.cardID));
        cardsRepo.findFirstById(command.cardID).markFromCustomer = false;

        usersRepo.flush();
        cardsRepo.flush();

        return true;
    }
}
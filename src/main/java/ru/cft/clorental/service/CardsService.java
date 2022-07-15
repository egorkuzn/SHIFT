package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.Translator;
import ru.cft.clorental.model.request_forms.CardMessage;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.repos.model.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CardsService {
    final CardsRepo cardsRepo;
    final UsersRepo usersRepo;
    HashMap<String, String> translator = new HashMap<>();

    @Autowired
    CardsService(CardsRepo cardsRepo, UsersRepo usersRepo){
        this.cardsRepo = cardsRepo;
        this.usersRepo = usersRepo;
    }

    public CardMessage findById(Long id){
        CardEntity card = cardsRepo.findFirstById(id);
        UserEntity owner = usersRepo.findFirstByIdAndVerified(card.ownerID, true);

        UserEntity customer = null;

        if(card.customerId != null)
            customer = usersRepo.findFirstByIdAndVerified(card.customerId, true);

        return new CardMessage(card, owner, customer);
    }

    public List<CardMessage> findByCategory(String category) {
        List<CardMessage> list = new ArrayList<>();

        cardsRepo.findAllByCategoryAndRent(Translator.get(category), false).forEach(
                cardEntity -> list.add(new CardMessage(cardEntity,
                        usersRepo.findFirstById(cardEntity.ownerID),
                        usersRepo.findFirstById(cardEntity.customerId))
                )
        );

        return list;
    }

    public List<CardMessage> showAll() {
        ArrayList<CardMessage> cardMessages = new ArrayList<>();
        List<CardEntity> cardEntities = cardsRepo.findAllByRent(false);

        cardEntities.forEach(elem -> cardMessages.add(new CardMessage(elem,
                usersRepo.findFirstById(elem.ownerID),
                usersRepo.findFirstById(elem.customerId))));

        return cardMessages;
    }
}

package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.request_forms.CardMessage;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.repos.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService {
    final CardsRepo cardsRepo;
    final UsersRepo usersRepo;

    @Autowired
    public FeedService(CardsRepo cardsRepo, UsersRepo usersRepo){
        this.cardsRepo = cardsRepo;
        this.usersRepo = usersRepo;
    }
    public List<CardMessage> getFromTo(Long from, Long to) {
        ArrayList<CardMessage> cardMessages = new ArrayList<>();

        for(Long i = from; i <= to ; i++) {
            CardEntity card;

            if((card = cardsRepo.findFirstByIdAndRent(i, false)) != null) {
                UserEntity owner = usersRepo.findFirstByIdAndVerified(card.ownerID, true);
                UserEntity customer = null;

                if (card.customerId != null)
                    customer = usersRepo.findFirstByIdAndVerified(card.customerId, true);

                cardMessages.add(new CardMessage(card, owner, customer));
            }
        }

        return cardMessages;
    }
}

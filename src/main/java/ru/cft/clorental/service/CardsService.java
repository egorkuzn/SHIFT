package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.model.CardEntity;

@Service
public class CardsService {
    final CardsRepo cardsRepo;

    @Autowired
    CardsService(CardsRepo cardsRepo){
        this.cardsRepo = cardsRepo;
    }

    public CardEntity findById(Long id){
        return cardsRepo.findFirstById(id);
    }
}

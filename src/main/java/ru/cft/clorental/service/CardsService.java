package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.model.CardEntity;

import java.util.ArrayList;
import java.util.List;

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

    public List<Long> findByCategory(String category) {
        List<Long> list = new ArrayList<>();

        cardsRepo.findAllByCategoryAndRent(category, false).forEach(
                cardEntity -> list.add(cardEntity.id)
        );

        return list;
    }
}

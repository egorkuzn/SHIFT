package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.CardsService;

@RestController
@RequestMapping("cards")
public class Cards {
    CardsService cardsService;

    @Autowired
    public Cards(CardsService cardsService){
        this.cardsService = cardsService;
    }

    @GetMapping
    public ResponseEntity<CardEntity> getCardById(@Param("id") Long id){
        return ResponseEntity.ok().body(cardsService.findById(id));
    }
}

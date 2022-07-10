package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.CardsService;

@RestController
@RequestMapping("/cards")
public class CardsResource {
    CardsService cardsService;

    @Autowired
    public CardsResource(CardsService cardsService){
        this.cardsService = cardsService;
    }

    @GetMapping
    public ResponseEntity<CardEntity> getCard(@RequestBody Long id){
        return ResponseEntity.ok().body(cardsService.findById(id));
    }
}

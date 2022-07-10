package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.request_forms.CardChangeCommand;
import ru.cft.clorental.model.request_forms.NewCardForm;
import ru.cft.clorental.model.request_forms.RequestForGettingCardsOfOneType;
import ru.cft.clorental.service.impl.RentCardsService;

import java.util.List;


@RestController
@RequestMapping("me/rent")
public class MeRent {
    RentCardsService rentCardsService;

    @Autowired
    public MeRent(RentCardsService rentCardsService){
        this.rentCardsService = rentCardsService;
    }


    @PostMapping
    public ResponseEntity<List<Long>> getCardsOfUser(@RequestBody RequestForGettingCardsOfOneType request){
        return ResponseEntity.ok().body(rentCardsService.getCards(request));
    }

    @PutMapping
    public ResponseEntity<Boolean> addCard(@RequestBody NewCardForm newCard){
        return ResponseEntity.ok().body(rentCardsService.addNewCard(newCard));
    }

    @PatchMapping
    public ResponseEntity<Boolean> changeCard(@RequestBody CardChangeCommand command) {
        return ResponseEntity.ok().body(rentCardsService.makeChanges(command));
    }
}


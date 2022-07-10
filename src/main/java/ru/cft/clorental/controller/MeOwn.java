package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.CardChangeCommand;
import ru.cft.clorental.model.NewCardForm;
import ru.cft.clorental.model.RequestForGettingCardsOfOneType;
import ru.cft.clorental.service.impl.OwnCardsService;

import java.util.List;


@RestController
@RequestMapping("me/own")
public class MeOwn {
    OwnCardsService ownCardsService;

    @Autowired
    public MeOwn(OwnCardsService ownCardsService){
        this.ownCardsService = ownCardsService;
    }


    @PostMapping
    public ResponseEntity<List<Long>> getCardsOfUser(@RequestBody RequestForGettingCardsOfOneType request){
        return ResponseEntity.ok().body(ownCardsService.getCards(request));
    }

    @PutMapping
    public ResponseEntity<Boolean> addCard(@RequestBody NewCardForm newCard){
        return ResponseEntity.ok().body(ownCardsService.addNewCard(newCard));
    }

    @PatchMapping
    public ResponseEntity<Boolean> changeCard(@RequestBody CardChangeCommand command) {
        return ResponseEntity.ok().body(ownCardsService.makeChanges(command));
    }
}


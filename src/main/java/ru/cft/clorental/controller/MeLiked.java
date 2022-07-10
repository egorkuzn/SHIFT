package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.CardChangeCommand;
import ru.cft.clorental.model.NewCardForm;
import ru.cft.clorental.model.RequestForGettingCardsOfOneType;
import ru.cft.clorental.service.impl.LikedCardsService;

import java.util.List;


@RestController
@RequestMapping("me/liked")
public class MeLiked {
    LikedCardsService likedCardsService;

    @Autowired
    public MeLiked(LikedCardsService likedCardsService){
        this.likedCardsService = likedCardsService;
    }


    @PostMapping
    public ResponseEntity<List<Long>> getCards(@RequestBody RequestForGettingCardsOfOneType request){
        return ResponseEntity.ok().body(likedCardsService.getCards(request));
    }

    @PutMapping
    public ResponseEntity<Boolean> addCard(@RequestBody NewCardForm newCard){
        return ResponseEntity.ok().body(likedCardsService.addNewCard(newCard));
    }

    @PatchMapping
    public ResponseEntity<Boolean> changeCard(@RequestBody CardChangeCommand command) {
        return ResponseEntity.ok().body(likedCardsService.makeChanges(command));
    }

}


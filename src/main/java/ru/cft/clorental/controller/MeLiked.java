package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.UserIDCardID;
import ru.cft.clorental.model.request_forms.CardChangeCommand;
import ru.cft.clorental.model.request_forms.NewCardForm;
import ru.cft.clorental.model.request_forms.RequestForGettingCardsOfOneType;
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
    public ResponseEntity<Boolean> addCard(@RequestBody UserIDCardID newCard){
        return ResponseEntity.ok().body(likedCardsService.addNewCard(newCard));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> changeCard(@RequestBody UserIDCardID command) {
        return ResponseEntity.ok().body(likedCardsService.delete(command));
    }

}


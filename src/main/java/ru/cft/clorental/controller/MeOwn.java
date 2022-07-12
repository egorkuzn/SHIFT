package ru.cft.clorental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.UserIDCardID;
import ru.cft.clorental.model.request_forms.CardChangeCommand;
import ru.cft.clorental.model.request_forms.NewCardForm;
import ru.cft.clorental.model.request_forms.RequestForGettingCardsOfOneType;
import ru.cft.clorental.service.impl.OwnCardsService;

import java.util.List;


@RestController
@RequestMapping("me/own")
@Api(value = "Owned cards")
public class MeOwn {
    OwnCardsService ownCardsService;

    @Autowired
    public MeOwn(OwnCardsService ownCardsService){
        this.ownCardsService = ownCardsService;
    }


    @PostMapping
    @ApiOperation("Getting user's own cards")
    public ResponseEntity<List<Long>> getCardsOfUser(@RequestBody RequestForGettingCardsOfOneType request){
        return ResponseEntity.ok().body(ownCardsService.getCards(request));
    }

    @PutMapping
    @ApiOperation("New card creation")
    public ResponseEntity<Boolean> addCard(@RequestBody NewCardForm newCard){
        return ResponseEntity.ok().body(ownCardsService.addNewCard(newCard));
    }

    @PatchMapping
    @ApiOperation("Changing card param")
    public ResponseEntity<Boolean> changeCard(@RequestBody CardChangeCommand command) {
        return ResponseEntity.ok().body(ownCardsService.makeChanges(command));
    }

    @DeleteMapping
    @ApiOperation("Deleting from cards")
    public ResponseEntity<Boolean> deleteCard(@RequestBody UserIDCardID command) {
        return ResponseEntity.ok().body(ownCardsService.delete(command));
    }
}


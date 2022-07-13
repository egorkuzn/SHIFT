package ru.cft.clorental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.request_forms.UserIDCardID;
import ru.cft.clorental.model.request_forms.RequestForGettingCardsOfOneType;
import ru.cft.clorental.service.impl.RentCardsService;

import java.util.List;


@RestController
@RequestMapping("me/rent")
@Api(value = "Rent cards")
public class MeRent {
    final RentCardsService rentCardsService;

    @Autowired
    public MeRent(RentCardsService rentCardsService){
        this.rentCardsService = rentCardsService;
    }

    @ApiOperation("Getting rent cards")
    @PostMapping
    public ResponseEntity<List<Long>> getCardsOfUser(@RequestBody RequestForGettingCardsOfOneType request){
        return ResponseEntity.ok().body(rentCardsService.getCards(request));
    }

    @ApiOperation("Adding rent card")
    @PutMapping
    public ResponseEntity<Boolean> addCard(@RequestBody UserIDCardID newCard){
        return ResponseEntity.ok().body(rentCardsService.addNewCard(newCard));
    }

    @ApiOperation("Finishing of rent")
    @DeleteMapping
    public ResponseEntity<Boolean> changeCard(@RequestBody UserIDCardID command) {
        return ResponseEntity.ok().body(rentCardsService.delete(command));
    }
}


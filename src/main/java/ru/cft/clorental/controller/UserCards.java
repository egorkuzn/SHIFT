package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.CardChangeCommand;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.model.RequestForGettingCardsOfOneType;
import ru.cft.clorental.service.UserCardsService;
import java.util.List;

@RestController
@RequestMapping("cards")
public class UserCards {
    UserCardsService userCardsService;

    @Autowired
    public UserCards(UserCardsService userCardsService){
        this.userCardsService = userCardsService;
    }

    @GetMapping("{username}")
    public ResponseEntity<List<Long>> getCardsOfOneType(@RequestBody RequestForGettingCardsOfOneType request){
        return ResponseEntity.ok().body(userCardsService.getCards(request));
    }

    @PostMapping("{username}")
    public ResponseEntity<Boolean> addCard(@RequestBody CardEntity newCard){
        return ResponseEntity.ok().body(userCardsService.addNewCard(newCard));
    }

    @PutMapping("{username}")
    public ResponseEntity<Boolean> changeCard(@RequestBody CardChangeCommand command) {
        return ResponseEntity.ok().body(userCardsService.makeChanges(command));
    }
}


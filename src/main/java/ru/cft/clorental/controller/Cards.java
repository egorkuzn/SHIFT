package ru.cft.clorental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.request_forms.CardMessage;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.CardsService;

import java.util.List;

@RestController
@RequestMapping("cards")
@Api(value = "cards")
public class Cards {
    final CardsService cardsService;

    @Autowired
    public Cards(CardsService cardsService){
        this.cardsService = cardsService;
    }

    @ApiOperation("Taking card by id")
    @GetMapping
    public @ResponseBody ResponseEntity<CardMessage> getCardById(@Param("id") Long id){
        return ResponseEntity.ok().body(cardsService.findById(id));
    }

    @ApiOperation("Taking cards IDs by category")
    @GetMapping("{category}")
    public ResponseEntity<List<Long>> getCardsByCategory(@PathVariable String category){
        return ResponseEntity.ok().body(cardsService.findByCategory(category));
    }
}

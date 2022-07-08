package ru.cft.clorental.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.domain.CardEntity;
import ru.cft.clorental.repos.CardsRepo;

@RestController
@RequestMapping("/cards")
public class CardsResource {
    public CardsRepo cardsRepo;

    @GetMapping
    public ResponseEntity<CardEntity> getCard(Long id){
        return ResponseEntity.ok().body(cardsRepo.findById(id).get());
    }
}
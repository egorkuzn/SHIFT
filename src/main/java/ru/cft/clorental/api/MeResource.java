package ru.cft.clorental.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.domain.CardChangeCommand;
import ru.cft.clorental.domain.CardEntity;
import ru.cft.clorental.domain.RequestForGettingCardsOfOneType;
import ru.cft.clorental.repos.CardsRepo;

import java.sql.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/me")
public class MeResource {
    CardsRepo cardsRepo;

    @GetMapping("/cards")
    public ResponseEntity<List<Long>> getCardsOfOneType(@RequestBody RequestForGettingCardsOfOneType request){
        return ResponseEntity.ok().body(
                cardsRepo
                        .findAllByOAndOwnerIDAndAndCategory(
                                request.id,
                                request.type)
        );
    }

    @PostMapping("/cards")
    public ResponseEntity<?> addCard(@RequestBody CardEntity newCard){
        return ResponseEntity.ok().body(cardsRepo.save(newCard));
    }

    @PutMapping("/cards")
    public HttpStatus changeCard(@RequestBody CardChangeCommand command){
        CardEntity modifyingCard = cardsRepo.findById(command.cardID).get();

        if(modifyingCard.getOwnerID().equals(command.userID)) {
            cardsRepo.delete(modifyingCard);

            switch (command.what) {
                case "image":
                    modifyingCard.setImage(command.onWhat);
                    break;
                case "price":
                    modifyingCard.setPrice(Double.parseDouble(command.onWhat));
                    break;
                case "ownedId":
                    modifyingCard.setOwnerID(Long.parseLong(command.onWhat));
                    break;
                case "isRent":
                    modifyingCard.setRent(Boolean.parseBoolean(command.onWhat));
                    break;
                case "customerID":
                    modifyingCard.setCustormerId(Long.parseLong(command.onWhat));
                    break;
                case "term":
                    modifyingCard.setTerm(Date.valueOf(command.onWhat));
                    break;
                case "category":
                    modifyingCard.setCategory(command.onWhat);
            }

            cardsRepo.save(modifyingCard);
            return OK;
        }

        return FORBIDDEN;
    }
}

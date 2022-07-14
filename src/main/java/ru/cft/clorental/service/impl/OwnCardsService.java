package ru.cft.clorental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.cft.clorental.model.request_forms.*;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.repos.model.ImageEntity;
import ru.cft.clorental.repos.model.UserEntity;
import ru.cft.clorental.service.ImageLoaderService;
import ru.cft.clorental.service.MeCardsService;

import java.sql.Date;
import java.util.Set;

@Service
public class OwnCardsService extends MeCardsService {
    final ImageLoaderService imageService;
    @Autowired
    public OwnCardsService(CardsRepo cardsRepo, UsersRepo usersRepo, ImageLoaderService imageService) {
        super(cardsRepo, usersRepo);
        this.imageService = imageService;
    }

    @Override
    public boolean delete(UserIDCardID command) {
        usersRepo.findFirstByIdAndVerified(command.userID, true).own.remove(cardsRepo.findFirstById(command.cardID));
        CardEntity card = cardsRepo.findFirstById(command.cardID);
        if(!card.markFromCustomer) {
            card.rent = false;
            card.markFromOwner = false;

            for(ImageEntity elem: card.images){
                card.images.remove(elem);
                imageService.delete(elem);
            }

            cardsRepo.deleteById(command.cardID);
            cardsRepo.flush();

            return true;
        }
        else
            return false;
    }

    @Override
    protected Set<CardEntity> returnTypeSet(Long userID) {
        return usersRepo.findFirstByIdAndVerified(userID, true).own;
    }

    public boolean makeChanges(CardChangeCommand command) {
        CardEntity card = cardsRepo.findFirstByOwnerIDAndId(command.userID, command.cardID);

        if(!card.rent) {
            switch (command.what) {
                case "term" -> card.term = Date.valueOf(command.onWhat);
                case "price" -> card.price = Double.parseDouble(command.onWhat);
                case "rentStatus" -> {
                    card.markFromOwner = Boolean.getBoolean(command.onWhat);

                    if(card.markFromCustomer && card.markFromOwner)
                        card.rent = true;

                    if(!card.markFromCustomer && !card.markFromOwner)
                        card.rent = false;
                }
                case "category" -> card.category = command.onWhat;
                case "customerID" -> card.customerId = Long.parseLong(command.onWhat);
                case "ownerID" -> card.ownerID = Long.parseLong(command.onWhat);
                case "description" -> card.description = command.onWhat;
                default -> {return false;}
            }

            return true;
        }

        return false;
    }

    public boolean addNewCard(NewCardForm form){
        UserEntity user = usersRepo.findFirstByIdAndVerified(form.userID, true);

        if(user.own.size() == user.maxOwnCount)
            return false;

        CardEntity card = generatedNewCard(form);
        cardsRepo.save(card);
        cardsRepo.flush();
        user.own.add(card);
        usersRepo.flush();
        return true;
    }

    protected CardEntity generatedNewCard(NewCardForm form) {
        CardEntity cardEntity = new CardEntity();

        cardEntity.ownerID = form.userID;
        cardEntity.category = form.category;
        cardEntity.description = form.description;
        cardEntity.price = form.price;
        cardEntity.term = form.term;
        cardEntity.rent = false;
        return cardEntity;
    }

    public boolean addPictureToCard(MultipartFile imageFile, long userID, long cardID) {
        CardEntity card;

        if((card = cardsRepo.findFirstByIdAndRentAndOwnerID(cardID, false, userID)) != null){
            card.images.add(imageService.generate(imageFile));
            cardsRepo.flush();
            return true;
        }

        return false;
    }

    public boolean deletePictureInCard(FormToDeletePictureInCard request) {
        CardEntity card;

        if((card = cardsRepo.findFirstByIdAndRentAndOwnerID(request.cardID, false, request.userID)) != null){
            for(ImageEntity elem : card.images)
                if(elem.imageURL.equals(request.imageURL)) {
                    card.images.remove(elem);
                    imageService.delete(elem);
                    cardsRepo.flush();
                    return true;
                }

        }

        return false;
    }
}

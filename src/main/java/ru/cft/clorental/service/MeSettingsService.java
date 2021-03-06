package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.SecurityBlock;
import ru.cft.clorental.model.Validator;
import ru.cft.clorental.model.request_forms.FormForUserDeleting;
import ru.cft.clorental.model.request_forms.RequestToChangeUserParam;
import ru.cft.clorental.model.request_forms.RequestToGetUserParam;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.repos.model.ImageEntity;
import ru.cft.clorental.repos.model.UserEntity;

@Service
public class MeSettingsService {
    private final UsersRepo usersRepo;
    private final CardsRepo cardsRepo;
    private final ImageLoaderService imageService;
    @Autowired
    public MeSettingsService(UsersRepo usersRepo, CardsRepo cardsRepo, ImageLoaderService imageService){
        this.usersRepo = usersRepo;
        this.cardsRepo = cardsRepo;
        this.imageService = imageService;
    }

    public String getUserParam(RequestToGetUserParam request) {
        UserEntity user = usersRepo.findFirstById(request.userID);

        switch (request.paramName){
            case "email" -> {return user.email;}
            case "name" -> {return  user.name;}
            case "surname" -> {return user.surname;}
            case "phone" -> {return user.phone;}
            default -> {return "Bad param name";}
        }
    }

    public boolean changeUserParam(RequestToChangeUserParam request) {
        UserEntity userEntity = usersRepo.findFirstByIdAndVerified(request.userID, true);
        boolean validVar = true;

        if(userEntity.id.equals(request.userID))
            switch(request.paramName){
                case "email" -> {
                    if(Validator.isValidEmail(request.paramValue))
                        userEntity.email = request.paramValue;
                    else validVar = false;
                }
                case "name" -> {
                    if(Validator.isValidName(request.paramValue))
                        userEntity.name = request.paramValue;
                    else validVar = false;
                }
                case "surname" -> {
                    if(Validator.isValidSurname(request.paramValue))
                        userEntity.surname = request.paramValue;
                    else validVar = false;
                }
                case "phone" -> {
                    if(Validator.isValidPhone(request.paramValue))
                        userEntity.phone = request.paramValue;
                    else validVar = false;
                }
                default -> {return false;}
            }

        usersRepo.flush();
        return validVar;
    }

    public boolean delete(FormForUserDeleting request) {
        UserEntity user;

        if ((user = usersRepo.findFirstByIdAndEmailAndHash(request.id, request.email, SecurityBlock.getHash(request.password))) != null) {
            if(!clearOwnCards(user))
                return false;

            if(user.personalIcon != null)
                imageService.delete(user.personalIcon);

            usersRepo.delete(user);
            return true;
        }

        return false;
    }

    private boolean clearOwnCards(UserEntity user) {
        for(CardEntity elem: user.rent)
            if(elem.rent)
                return false;

        for(CardEntity elem: user.own)
            if(elem.rent)
                return false;

        for(CardEntity elem: user.own){
            user.own.remove(elem);

            for(ImageEntity image: elem.images) {
                elem.images.remove(image);
                imageService.delete(image);
            }

            cardsRepo.delete(elem);
            cardsRepo.flush();
        }

        return true;
    }
}

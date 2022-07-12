package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.Validator;
import ru.cft.clorental.model.request_forms.RequestToChangeUserParam;
import ru.cft.clorental.model.request_forms.RequestToGetUserParam;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.UserEntity;

@Service
public class MeSettingsService {
    UsersRepo usersRepo;
    @Autowired
    public MeSettingsService(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
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
            switch(request.whatChange){
                case "email" -> {
                    if(Validator.isValidEmail(request.onWhatChange))
                        userEntity.email = request.onWhatChange;
                    else validVar = false;
                }
                case "name" -> {
                    if(Validator.isValidName(request.onWhatChange))
                        userEntity.name = request.onWhatChange;
                    else validVar = false;
                }
                case "surname" -> {
                    if(Validator.isValidSurname(request.onWhatChange))
                        userEntity.surname = request.onWhatChange;
                    else validVar = false;
                }
                case "phone" -> {
                    if(Validator.isValidPhone(request.onWhatChange))
                        userEntity.phone = request.onWhatChange;
                    else validVar = false;
                }
                case "userIconURL" -> {
                    userEntity.userIconURL = request.onWhatChange;
                }
                default -> {return false;}
            }

        usersRepo.flush();
        return validVar;
    }
}

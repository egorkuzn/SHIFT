package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.RequestToChangeUserParam;
import ru.cft.clorental.model.RequestToGetUserParam;
import ru.cft.clorental.model.SecurityBlock;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.UserEntity;

@Service
public class UserSettingsService {
    UsersRepo usersRepo;
    @Autowired
    public UserSettingsService(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
    }

    public String getUserParam(RequestToGetUserParam request) {
        UserEntity user = usersRepo.findById(request.userID).get();

        switch (request.paramName){
            case "email" -> {return user.email;}
            case "name" -> {return  user.name;}
            case "surname" -> {return user.surname;}
            case "phone" -> {return user.phone;}
            default -> {return "";}
        }
    }

    public boolean changeUserParam(RequestToChangeUserParam request) {
        UserEntity userEntity = usersRepo.findById(request.userID).get();
        switch(request.whatChange){
            case "email" -> userEntity.email = request.onWhatChange;
            case "name" -> userEntity.name = request.onWhatChange;
            case "surname" -> userEntity.surname = request.onWhatChange;
            case "phone" -> userEntity.phone = request.onWhatChange;
            default -> {}
        }

        return true;
    }
}

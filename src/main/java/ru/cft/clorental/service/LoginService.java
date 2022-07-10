package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.*;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.UserEntity;


@Service
public class LoginService {
    final UsersRepo usersRepo;

    @Autowired
    public LoginService(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
    }

    public Long getIdByForm(AuthorisationForm form) {
        return usersRepo.findByHashAndEmail(SecurityBlock.getHash(form.password), form.email).id;
    }

    public Long registration(RegistrationForm form) {
        UserEntity user = newUser(form);
        usersRepo.save(user);
        return user.id;
    }

    public Long confirming(ConfirmingForm form){
        return form.tempID;
    }

    UserEntity newUser(RegistrationForm form){
        UserEntity user = new UserEntity();
        user.hash = form.password;
        user.email = form.email;
        user.name = form.name;
        user.surname = form.surname;
        user.phone = form.phone;

        return user;
    }
}

package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.*;
import ru.cft.clorental.model.request_forms.AuthorisationForm;
import ru.cft.clorental.model.request_forms.ConfirmingForm;
import ru.cft.clorental.model.request_forms.RegistrationForm;
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
        return usersRepo.findFirstByHashAndEmail(SecurityBlock.getHash(form.password), form.email).id;
    }

    public Long registration(RegistrationForm form) {
        UserEntity user = newUser(form);

        if(user != null) {
            usersRepo.save(user);
            return user.id;
        } else
            return null;
    }

    public Long confirming(ConfirmingForm form){
        return form.tempID;
    }

    UserEntity newUser(RegistrationForm form){
        if(isValidForm(form)) {
            UserEntity user = new UserEntity();
            user.hash = form.password;
            user.email = form.email;
            user.name = form.name;
            user.surname = form.surname;
            user.phone = form.phone;

            return user;
        }

        return null;
    }

    private boolean isValidForm(RegistrationForm form) {
        return Validator.isValidPassword(form.password) &&
                Validator.isValidEmail(form.email) &&
                Validator.isValidName(form.name) &&
                Validator.isValidSurname(form.surname) &&
                Validator.isValidPhone(form.phone);
    }
}

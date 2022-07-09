package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.AuthorisationForm;
import ru.cft.clorental.model.ConfirmingForm;
import ru.cft.clorental.model.MailSender;
import ru.cft.clorental.model.RegistrationForm;
import ru.cft.clorental.repos.TermRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.TermEntity;
import ru.cft.clorental.repos.model.UserEntity;


@Service
public class LoginService {
    UsersRepo usersRepo;
    TermRepo termRepo;

    @Autowired
    public LoginService(UsersRepo usersRepo, TermRepo termRepo){
        this.usersRepo = usersRepo;
        this.termRepo = termRepo;
    }

    public Long getIdByForm(AuthorisationForm form) {
        return usersRepo.findByHashAndEmail(getHash(form.password), form.email).id;
    }

    public Long registration(RegistrationForm form) {
        TermEntity user = newUserEntity(form);
        termRepo.save(user);
        sendCodeToEmail(user);
        return user.id;
    }

    public Long confirming(ConfirmingForm form){
        if(termRepo.findByEmailCode(form.emailCode).id.equals(form.tempID)) {
            UserEntity found = termRepo.findByEmailCode(form.emailCode).userEntity;
            usersRepo.save(found);
            return found.id;
        }

        return null;
    }

    void sendCodeToEmail(TermEntity user){
        MailSender.send(user.userEntity.email, user.emailCode);
    }

    public TermEntity newUserEntity(RegistrationForm form){
        UserEntity userEntity = new UserEntity();
        userEntity.email = form.email;
        userEntity.name = form.name;
        userEntity.phone = form.phone;
        userEntity.surname = form.surname;
        userEntity.hash = getHash(form.password);

        TermEntity termEntity = new TermEntity();
        termEntity.userEntity = userEntity;
        return termEntity;
    }



    public String getHash(String password){
        return password;
    }
}

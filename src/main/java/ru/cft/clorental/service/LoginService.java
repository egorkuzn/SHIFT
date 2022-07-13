package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.cft.clorental.model.SecurityBlock;
import ru.cft.clorental.model.Validator;
import ru.cft.clorental.model.request_forms.AuthorisationForm;
import ru.cft.clorental.model.request_forms.ConfirmingForm;
import ru.cft.clorental.model.request_forms.RegistrationForm;
import ru.cft.clorental.model.request_forms.UserMessage;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.UserEntity;

@Service
public class LoginService {
    final ImageLoaderService imageService;
    final UsersRepo usersRepo;
    final EmailService emailService;

    @Autowired
    public LoginService(ImageLoaderService imageService, UsersRepo usersRepo, EmailService emailService){
        this.imageService = imageService;
        this.usersRepo = usersRepo;
        this.emailService = emailService;
    }

    public UserMessage getIdByForm(AuthorisationForm form) {
        UserEntity user = null;

        if((user = usersRepo.findFirstByHashAndEmailAndVerified(SecurityBlock.getHash(form.password), form.email, true)) != null)
            return new UserMessage(user);
        else
            return null;
    }

    public Long registration(RegistrationForm form, MultipartFile userIcon){
        UserEntity user = newUser(form, userIcon);

        if(user != null && usersRepo.findAllByEmail(user.email).isEmpty()) {
            usersRepo.save(user);
            String emailCode = Validator.stringRand();
            emailService.sendMail(user.email, "Регистрация", "Код подтверждения: " + emailCode);
            user.emailCode = emailCode;
            usersRepo.flush();
            return user.id;
        } else
            return null;
    }


    public Long confirming(ConfirmingForm form){
        usersRepo.findFirstByIdAndEmailCode(form.tempID, form.emailCode).verified = true;
        usersRepo.flush();

        return form.tempID;
    }

    UserEntity newUser(RegistrationForm form, MultipartFile userIcon){
        if(isValidForm(form)) {
            UserEntity user = new UserEntity();
            user.hash = form.password;
            user.email = form.email;
            user.name = form.name;
            user.surname = form.surname;
            user.phone = form.phone;
            user.personalIcon = imageService.generate(userIcon);
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

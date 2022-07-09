package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.AuthorisationForm;
import ru.cft.clorental.model.ConfirmingForm;
import ru.cft.clorental.model.RegistrationForm;
import ru.cft.clorental.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping
    public ResponseEntity<Long> getIdByLoginPassword(AuthorisationForm form){
        return ResponseEntity.ok().body(loginService.getIdByForm(form));
    }

    @PostMapping
    public ResponseEntity<Long> registration(RegistrationForm form){
        return ResponseEntity.ok().body(loginService.registration(form));
    }

    @PutMapping
    public ResponseEntity<Long> confirming(ConfirmingForm form){
        return ResponseEntity.ok().body(loginService.confirming(form));
    }
}

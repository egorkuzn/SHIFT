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
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PatchMapping
    @ResponseBody
    public ResponseEntity<Long> getIdByLoginPassword(@RequestBody AuthorisationForm form){
        return ResponseEntity.ok().body(loginService.getIdByForm(form));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Long> registration(@RequestBody RegistrationForm form){
        return ResponseEntity.ok().body(loginService.registration(form));
    }

    @PutMapping
    @ResponseBody

    public ResponseEntity<Long> confirming(@RequestBody ConfirmingForm form){
        return ResponseEntity.ok().body(loginService.confirming(form));
    }
}

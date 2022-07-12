package ru.cft.clorental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.request_forms.AuthorisationForm;
import ru.cft.clorental.model.request_forms.ConfirmingForm;
import ru.cft.clorental.model.request_forms.RegistrationForm;
import ru.cft.clorental.service.LoginService;

@RestController
@RequestMapping("login")
@Api(value = "login")
public class Login {
    private final LoginService loginService;

    @Autowired
    public Login(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping
    @ResponseBody
    @ApiOperation("Check")
    public ResponseEntity<String> getIdByLoginPassword(){
        return ResponseEntity.ok().body("Привет дружок пирожок)))");
    }

    @PostMapping
    @ResponseBody
    @ApiOperation("Authorization")
    public ResponseEntity<Long> getIdByLoginPassword(@RequestBody AuthorisationForm form){
        return ResponseEntity.ok().body(loginService.getIdByForm(form));
    }

    @PutMapping
    @ResponseBody
    @ApiOperation("Registration")
    public ResponseEntity<Long> registration(@RequestBody RegistrationForm form){
        return ResponseEntity.ok().body(loginService.registration(form));
    }

    @PatchMapping
    @ResponseBody
    @ApiOperation("Verification")
    public ResponseEntity<Long> confirming(@RequestBody ConfirmingForm form){
        return ResponseEntity.ok().body(loginService.confirming(form));
    }
}
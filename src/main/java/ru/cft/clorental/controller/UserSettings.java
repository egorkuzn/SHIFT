package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.RequestToChangeUserParam;
import ru.cft.clorental.model.RequestToGetUserParam;
import ru.cft.clorental.service.UserSettingsService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserSettings {
    private UserSettingsService userSettingsService;
    @Autowired
    UserSettings(UserSettingsService userSettingsService){
        this.userSettingsService = userSettingsService;
    }
    @GetMapping("{username}")
    public ResponseEntity<String> getInfoAboutUserParam(@RequestBody RequestToGetUserParam request){
        return ResponseEntity.ok().body(userSettingsService.getUserParam(request));
    }

    @PutMapping("{username}")
    public ResponseEntity<Boolean> changeCard(@RequestBody RequestToChangeUserParam request) {
        return ResponseEntity.ok().body(userSettingsService.changeUserParam(request));
    }
}

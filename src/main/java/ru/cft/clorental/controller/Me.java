package ru.cft.clorental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.RequestToChangeUserParam;
import ru.cft.clorental.model.RequestToGetUserParam;
import ru.cft.clorental.service.MeService;

@RestController
@RequestMapping("user")
public class Me {
    private MeService meService;
    @Autowired
    Me(MeService meService){
        this.meService = meService;
    }
    @PostMapping("{username}")
    public ResponseEntity<String> getInfoAboutUserParam(
            @PathVariable String username,
            @RequestBody RequestToGetUserParam request){
        return ResponseEntity.ok().body(meService.getUserParam(request));
    }

    @PutMapping("{username}")
    public ResponseEntity<Boolean> changeCard(
            @PathVariable String username,
            @RequestBody RequestToChangeUserParam request) {
        return ResponseEntity.ok().body(meService.changeUserParam(request));
    }
}

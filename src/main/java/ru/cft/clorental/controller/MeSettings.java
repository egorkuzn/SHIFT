package ru.cft.clorental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.request_forms.RequestToChangeUserParam;
import ru.cft.clorental.model.request_forms.RequestToGetUserParam;
import ru.cft.clorental.service.MeSettingsService;

@RestController
@RequestMapping("me/settings")
@Api(value = "Settings")
public class MeSettings {
    private MeSettingsService meService;
    @Autowired
    MeSettings(MeSettingsService meService){
        this.meService = meService;
    }

    @ApiOperation(value = "Getting info about user param")
    @PostMapping
    public ResponseEntity<String> getInfoAboutUserParam(@RequestBody RequestToGetUserParam request){
        return ResponseEntity.ok().body(meService.getUserParam(request));
    }

    @ApiOperation(value = "Changing user's param")
    @PutMapping
    public ResponseEntity<Boolean> changeCard(@RequestBody RequestToChangeUserParam request) {
        return ResponseEntity.ok().body(meService.changeUserParam(request));
    }
}

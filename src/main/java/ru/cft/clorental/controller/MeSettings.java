package ru.cft.clorental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.model.request_forms.FormForUserDeleting;
import ru.cft.clorental.model.request_forms.RequestToChangeUserParam;
import ru.cft.clorental.model.request_forms.RequestToGetUserParam;
import ru.cft.clorental.service.MeSettingsService;

@RestController
@RequestMapping("me/settings")
@Api(value = "Settings")
public class MeSettings {
    final MeSettingsService meService;
    @Autowired
    MeSettings(MeSettingsService meService){
        this.meService = meService;
    }

    @ApiOperation("Getting info about user param")
    @PostMapping
    public ResponseEntity<String> getInfoAboutUserParam(@RequestBody RequestToGetUserParam request){
        return ResponseEntity.ok().body(meService.getUserParam(request));
    }

    @ApiOperation("Changing user's param")
    @PutMapping
    public ResponseEntity<Boolean> changeUserParam(@RequestBody RequestToChangeUserParam request) {
        return ResponseEntity.ok().body(meService.changeUserParam(request));
    }

    @ApiOperation("Deleting of user")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@RequestBody FormForUserDeleting request) {
        return ResponseEntity.ok().body(meService.delete(request));
    }

}

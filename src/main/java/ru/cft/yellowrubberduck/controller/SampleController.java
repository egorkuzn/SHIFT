package ru.cft.yellowrubberduck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.yellowrubberduck.model.SecurityInfo;
import ru.cft.yellowrubberduck.repository.model.SampleEntity;
import ru.cft.yellowrubberduck.service.SampleService;

import java.util.List;

@RestController
@RequestMapping("login")
public class SampleController {

    private SampleService sampleService;
    private AuthorizatorService authorizator;

    @Autowired
    public SampleController(SampleService sampleService){
        this.sampleService = sampleService;
    }

    @GetMapping
    public List<SampleEntity> getAll() {
        return sampleService.getAllSample();
    }

    @PostMapping
    public int addUser(
            @RequestBody
            SecurityInfo securityInfo
    ){

        return id;
    }

    @PutMapping
    public boolean verifyUser(int tempId, String code){
        if(VerificationChecker.contains(tempId, code))
            addToGeneralUsersBase(tempId, code);

        return true;
    }

}

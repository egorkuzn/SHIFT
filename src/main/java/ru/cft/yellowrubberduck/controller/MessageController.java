package ru.cft.yellowrubberduck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.yellowrubberduck.model.Security;
import ru.cft.yellowrubberduck.model.User;
import ru.cft.yellowrubberduck.repos.GeneralUsersRepos;
import ru.cft.yellowrubberduck.repos.TemproraryUsersRepos;

import java.util.*;

@RestController
@RequestMapping("/login")
public class MessageController {
    MessageController(){}

    @Autowired
    private GeneralUsersRepos usersRepos;
    @Autowired
    private TemproraryUsersRepos temproraryUsersRepos;

    @GetMapping
    public UUID getUserId(Security security){

        usersRepos.;
    }

    @PostMapping
    public UUID newUserRegistration(Security security, User user){
        temproraryUsersRepos.save(user);
    }

    @PutMapping
    public UUID checkingAccountValidation(){

    }

    @DeleteMapping
    public boolean userDelete(@RequestBody User user){
        usersRepos.delete(user);
        return true;
    }
}

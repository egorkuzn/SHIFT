package ru.cft.yellowrubberduck.controller;

import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import org.springframework.web.bind.annotation.*;
import ru.cft.yellowrubberduck.model.Security;

import java.util.*;

@RestController
@RequestMapping("message")
public class MessageController {
    int counter = 0;
    public HashMap<Integer, String[]> dataBase = new HashMap<>();

    @GetMapping("{id}")
    public String[] list(@PathVariable int id){
        return dataBase.get(id);
    }

    @GetMapping
    public HashMap<Integer, String[]> getDataBase(){
        return dataBase;
    }

    @PostMapping
    public void addUser(@RequestBody Security securityData){

        dataBase.put(counter++, new String[]{securityData.password, securityData.email});
    }
}

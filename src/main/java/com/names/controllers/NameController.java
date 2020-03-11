package com.names.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.names.db.DataStore;
import com.names.model.Person;
import com.names.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NameController {

    @Autowired
    private Person person;

    @Autowired
    private DataStore dataStore;

    @Autowired
    private NameService nameService;

    @GetMapping(value = "/")
    public Map<Integer, Person> getAllPersons() {

        return dataStore.getPeopleData();
    }

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        return dataStore.getPerson(intId);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public String insertPerson(@RequestBody Person person) {

        Integer id = nameService.getNewId();
        if(id !=null)
            try {
                int intId = Integer.parseInt(String.valueOf(id));
                dataStore.addPerson(intId, person);
        } catch (NumberFormatException e) {
            System.out.println("This is not a number");
            System.out.println(e.getMessage());
        }
        return person.getFirstName() + "'s ID: " + id;
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public String changeFirstName(@PathVariable int id,
                                  @RequestBody String newPersonInfo) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(newPersonInfo, Person.class);

        dataStore.replaceInfo(id, person);
        return "ID #" + id + " has been updated.";
    }

    @DeleteMapping(value = "/{id}")
    public String deletePerson(@PathVariable int id) {
        dataStore.removePerson(id);
        return "ID #" + id + " has been deleted.";
    }

}
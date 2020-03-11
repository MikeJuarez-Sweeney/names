package com.names.controllers;

import com.names.db.DataStore;
import com.names.model.Person;
import com.names.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.Map;

@RestController
public class NameController {

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
    public String insertPerson(@RequestBody Person person) throws ConnectException {

        Integer id = nameService.getNewId();
        if (id != null)
            try {
                int intId = Integer.parseInt(String.valueOf(id));
                dataStore.addPerson(intId, person);
                return person.getFirstName() + "'s ID: " + id;
            } catch (NumberFormatException e) {
                System.out.println("This is not a number");
                System.out.println(e.getMessage());
            }
        return "Unable to add person.";
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public String changePersonInfo(@PathVariable int id,
                                  @RequestBody Person newPersonInfo) {

        dataStore.replaceInfo(id, newPersonInfo);
        return "ID #" + id + " has been updated.";
    }

    @DeleteMapping(value = "/{id}")
    public String deletePerson(@PathVariable int id) {
        dataStore.removePerson(id);
        return "ID #" + id + " has been deleted.";
    }
}
package com.names.controllers;

import com.names.DataStore;
import com.names.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NameController {

    @Autowired
    private Person person;

    @Autowired
    private DataStore dataStore;

    @GetMapping(value = "/")
    public Map<Integer, Person> getAllPersons() {

        return dataStore.getPeopleData();
    }

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        Person person = dataStore.getPerson(intId);
        return person;
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity insertPerson(@RequestBody Person person) {
        dataStore.addPerson(person);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity changeFirstName(@PathVariable int id,
                                          @RequestBody Person newPersonInfo) {
        dataStore.replaceInfo(id, newPersonInfo);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePerson(@PathVariable int id) {
        dataStore.removePerson(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}

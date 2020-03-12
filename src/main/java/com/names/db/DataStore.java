package com.names.db;

import com.names.model.Person;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataStore {

    private String person;

    private int id = 1000;

    private Map<Integer, Person> peopleData = new HashMap<>();

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void addPerson(int id, Person person) {
        peopleData.put(id, person);
    }

    public Person getPerson(Integer id) {
        return peopleData.get(id);
    }

    public void removePerson(Integer id) {
        peopleData.remove(id);
    }

    public Map<Integer, Person> getPeopleData() {
        return peopleData;
    }

    public void setPeopleData(Map<Integer, Person> peopleData) {
        this.peopleData = peopleData;
    }

    public void replaceInfo(int id, Person newPerson) {
        peopleData.replace(id, newPerson);
    }

    public boolean checkKey(Integer key) {
        return peopleData.containsKey(key);
    }

    public int createId() {

        int personId = id;
        id++;
        return personId;
    }
}
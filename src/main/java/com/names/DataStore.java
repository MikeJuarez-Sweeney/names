package com.names;

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

    public void addPerson(Person person) {
        peopleData.put(createId(), person);
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

    private int createId() {
        int personId = id;
        id++;
        return personId;
    }

    public void replaceInfo(int id, Person newPerson) {
        peopleData.replace(id, newPerson);
    }
/*    public void initializeData() {
        Person per1 = new Person();
        per1.setFirstName("George");
        per1.setLastName("Jetson");


        Person per2 = new Person();
        per2.setFirstName("Jim");
        per2.setLastName("Smith");

        peopleData.put(123, per1);
        peopleData.put(456, per2);
    }*/
}
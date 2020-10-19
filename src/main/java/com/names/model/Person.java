package com.names.model;

import org.springframework.stereotype.Component;

@Component
public class Person {

    private String firstName;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + status + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
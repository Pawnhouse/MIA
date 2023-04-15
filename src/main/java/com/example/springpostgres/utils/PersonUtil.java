package com.example.springpostgres.utils;

import com.example.springpostgres.models.Person;

public class PersonUtil {
    public static void updatePerson(Person oldPerson, Person newPerson) {
        if (newPerson != null) {
            oldPerson.setBirthday(newPerson.getBirthday());
            oldPerson.setName(newPerson.getName());
            oldPerson.setSurname(newPerson.getSurname());
            oldPerson.setMiddleName(newPerson.getMiddleName());
        }
    }
}

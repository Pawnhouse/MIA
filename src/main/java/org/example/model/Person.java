package org.example.model;

import java.util.Date;

public class Person implements Model {
    private int id;
    private String name;
    private String surname;
    private String middleName;
    private Date birthDay;

    public Person() {
    }

    public Person(int id, String name, String surname, String middleName, Date birthDay) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthDay = birthDay;
    }

    @Override
    public void show() {
        System.out.printf("id: %d\tname: %s %s %s \tbirthday: %s\n", id, name, surname, middleName, birthDay);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}

package com.example.springpostgres.models;

import jakarta.persistence.*;

@Entity
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(nullable = false)
    private Person person;
    @ManyToOne
    private Officer boss;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Department department;

    public Officer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Officer getBoss() {
        return boss;
    }

    public void setBoss(Officer boss) {
        this.boss = boss;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

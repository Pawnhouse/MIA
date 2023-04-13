package com.example.springpostgres.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Type type;
    @Column(length = 1000)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "crime_criminal",
            inverseJoinColumns = @JoinColumn(
                    name = "criminal_id", referencedColumnName = "id"
            )
    )
    private Set<Criminal> criminals;

    public Crime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Criminal> getCriminals() {
        return criminals;
    }

    public void setCriminals(Set<Criminal> criminals) {
        this.criminals = criminals;
    }
}

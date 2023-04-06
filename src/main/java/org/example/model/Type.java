package org.example.model;

public class Type implements Model {
    private int id;
    private String name;
    private String description;

    public Type() {
    }

    public Type(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public void show() {
        System.out.printf("id: %d\tname: %s\tdescription: %s", id, name, description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

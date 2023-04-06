package org.example.model;

public class Crime implements Model {
    private int id;
    private int typeId;
    private String description;

    public Crime() {
    }

    public Crime(int id, int typeId, String description) {
        this.id = id;
        this.typeId = typeId;
        this.description = description;
    }

    @Override
    public void show() {
        System.out.printf("id: %d\ttype_id: %d\tdescription: %s\n", id, typeId, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

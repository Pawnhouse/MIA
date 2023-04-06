package org.example.model;

import java.sql.Timestamp;

public class Complaint implements Model {
    private int id;
    private int senderId;
    private String description;
    private Timestamp sent;

    public Complaint() {
    }

    public Complaint(int id, int senderId, String description, Timestamp sent) {
        this.id = id;
        this.senderId = senderId;
        this.description = description;
        this.sent = sent;
    }

    @Override
    public void show() {
        System.out.printf("id: %d\taccount_id: %d\tdescription: %s\tsent: %s\n", id, senderId, description, sent);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getSent() {
        return sent;
    }

    public void setSent(Timestamp sent) {
        this.sent = sent;
    }
}

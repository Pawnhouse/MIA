package org.example.model;

public class Account implements Model {
    private int id;
    private int personId;
    private String email;
    private String password;
    private String role;

    public Account() {
    }

    public Account(int id, int personId, String email, String password, String role) {
        this.id = id;
        this.personId = personId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public void show() {
        System.out.printf("id: %d\tperson_id: %s\temail: ***\tpassword: ***\trole: %s\n", id, personId, role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

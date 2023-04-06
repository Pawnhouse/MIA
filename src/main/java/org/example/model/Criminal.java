package org.example.model;

public class Criminal implements Model {
    private int id;
    private int personId;
    private String nickname;
    private String biography;

    public Criminal() {
    }

    public Criminal(int id, int personId, String nickname, String biography) {
        this.id = id;
        this.personId = personId;
        this.nickname = nickname;
        this.biography = biography;
    }

    @Override
    public void show() {
        System.out.printf("id: %d\tperson_id: %d\tnickname: %s \tbiography: %s\n", id, personId, nickname, biography);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}

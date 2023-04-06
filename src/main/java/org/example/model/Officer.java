package org.example.model;

public class Officer implements Model {
    private int id;
    private int personId;
    private Integer bossId;
    private int departmentId;

    public Officer() {
    }

    public Officer(int id, int personId, Integer bossId, int departmentId) {
        this.id = id;
        this.personId = personId;
        this.bossId = bossId == 0 ? null : bossId;
        this.departmentId = departmentId;
    }

    @Override
    public void show() {
        System.out.printf(
                "id: %d\tpersonId: %d\tbossId: %d\tdepartmentId %d\n",
                id,
                personId,
                bossId,
                departmentId
        );
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

    public int getBossId() {
        return bossId;
    }

    public void setBossId(int bossId) {
        this.bossId = bossId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}

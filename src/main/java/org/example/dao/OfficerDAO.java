package org.example.dao;

import org.example.model.Model;
import org.example.model.Officer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficerDAO extends DAO {
    @Override
    protected String getTable() {
        return "officer";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"person_id", "boss_id", "department_id"};
    }

    @Override
    public String[] getUpdateTypes() {
        return new String[]{"int", "int", "int"};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{"id"};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Officer(
                rs.getInt("id"),
                rs.getInt("person_id"),
                rs.getInt("boss_id"),
                rs.getInt("department_id")
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Officer officer = (Officer) model;
        Object value = null;
        switch (name) {
            case "id" -> value = officer.getId();
            case "person_id" -> value = officer.getPersonId();
            case "boss_id" -> value = officer.getBossId();
            case "department_id" -> value = officer.getDepartmentId();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Officer();
        }
        Officer officer = (Officer) model;
        switch (name) {
            case "id" -> officer.setId((int) value);
            case "person_id" -> officer.setPersonId((int) value);
            case "boss_id" -> officer.setBossId((Integer) value);
            case "department_id" -> officer.setDepartmentId((int) value);
        }
        return model;
    }
}

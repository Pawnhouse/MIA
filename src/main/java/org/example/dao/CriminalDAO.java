package org.example.dao;

import org.example.model.Model;
import org.example.model.Criminal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CriminalDAO extends DAO {
    @Override
    protected String getTable() {
        return "criminal";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"person_id", "nickname", "biography"};
    }

    @Override
    public String[] getUpdateTypes() {
        return new String[]{"int", "string", "string"};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{"id"};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Criminal(
                rs.getInt("id"),
                rs.getInt("person_id"),
                rs.getString("nickname"),
                rs.getString("biography")
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Criminal criminal = (Criminal) model;
        Object value = null;
        switch (name) {
            case "id" -> value = criminal.getId();
            case "person_id" -> value = criminal.getPersonId();
            case "nickname" -> value = criminal.getNickname();
            case "biography" -> value = criminal.getBiography();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Criminal();
        }
        Criminal criminal = (Criminal) model;
        switch (name) {
            case "id" -> criminal.setId((int) value);
            case "person_id" -> criminal.setPersonId((int) value);
            case "nickname" -> criminal.setNickname((String) value);
            case "biography" -> criminal.setBiography((String) value);
        }
        return model;
    }
}

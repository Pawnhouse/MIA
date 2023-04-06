package org.example.dao;

import org.example.model.Model;
import org.example.model.Person;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO extends DAO {
    @Override
    protected String getTable() {
        return "person";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"name", "surname", "middle_name", "birthday"};
    }

    @Override
    public String[] getUpdateTypes() {
        return new String[]{"string", "string", "string", "date"};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{"id"};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Person(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("middle_name"),
                rs.getDate("birthday")
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Person person = (Person) model;
        Object value = null;
        switch (name) {
            case "id" -> value = person.getId();
            case "name" -> value = person.getName();
            case "surname" -> value = person.getSurname();
            case "middle_name" -> value = person.getMiddleName();
            case "birthday" -> value = person.getBirthDay();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Person();
        }
        Person person = (Person) model;
        switch (name) {
            case "id" -> person.setId((int) value);
            case "name" -> person.setName((String) value);
            case "surname" -> person.setSurname((String) value);
            case "middle_name" -> person.setMiddleName((String) value);
            case "birthday" -> person.setBirthDay((Date) value);
        }
        return model;
    }
}

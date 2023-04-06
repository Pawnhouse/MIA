package org.example.dao;

import org.example.model.Model;
import org.example.model.Person;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO extends DAO {
    private final String ID = "id";
    private final String NAME = "name";
    private final String SURNAME = "surname";
    private final String MIDDLE_NAME = "middle_name";
    private final String BIRTHDAY = "birthday";

    @Override
    protected String getTable() {
        return "person";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{NAME, SURNAME, MIDDLE_NAME, BIRTHDAY};
    }

    @Override
    public DataType[] getUpdateTypes() {
        return new DataType[]{DataType.STRING, DataType.STRING, DataType.STRING, DataType.DATE};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{ID};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Person(
                rs.getInt(ID),
                rs.getString(NAME),
                rs.getString(SURNAME),
                rs.getString(MIDDLE_NAME),
                rs.getDate(BIRTHDAY)
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Person person = (Person) model;
        Object value;
        switch (name) {
            case ID -> value = person.getId();
            case NAME -> value = person.getName();
            case SURNAME -> value = person.getSurname();
            case MIDDLE_NAME -> value = person.getMiddleName();
            case BIRTHDAY -> value = person.getBirthDay();
            default -> throw new IllegalArgumentException();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Person();
        }
        Person person = (Person) model;
        switch (name) {
            case ID -> person.setId((int) value);
            case NAME -> person.setName((String) value);
            case SURNAME -> person.setSurname((String) value);
            case MIDDLE_NAME -> person.setMiddleName((String) value);
            case BIRTHDAY -> person.setBirthDay((Date) value);
            default -> throw new IllegalArgumentException();
        }
        return model;
    }
}

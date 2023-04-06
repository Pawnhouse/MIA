package org.example.dao;

import org.example.model.Model;
import org.example.model.Type;

import java.sql.*;

public class TypeDAO extends DAO {
    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";

    @Override
    protected String getTable() {
        return "type";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{NAME, DESCRIPTION};
    }

    @Override
    public DataType[] getUpdateTypes() {
        return new DataType[]{DataType.STRING, DataType.STRING};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{ID};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Type(
                rs.getInt(ID),
                rs.getString(NAME),
                rs.getString(DESCRIPTION)
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Type type = (Type) model;
        Object value;
        switch (name) {
            case ID -> value = type.getId();
            case NAME -> value = type.getName();
            case DESCRIPTION -> value = type.getDescription();
            default -> throw new IllegalArgumentException();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Type();
        }
        Type type = (Type) model;
        switch (name) {
            case ID -> type.setId((int) value);
            case NAME -> type.setName((String) value);
            case DESCRIPTION -> type.setDescription((String) value);
            default -> throw new IllegalArgumentException();

        }
        return model;
    }
}

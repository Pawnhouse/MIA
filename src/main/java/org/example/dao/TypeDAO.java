package org.example.dao;

import org.example.model.Model;
import org.example.model.Type;

import java.sql.*;

public class TypeDAO extends DAO {
    @Override
    protected String getTable() {
        return "type";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"name", "description"};
    }

    @Override
    public String[] getUpdateTypes() {
        return new String[]{"string", "string"};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{"id"};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Type(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description")
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Type type = (Type) model;
        Object value = null;
        switch (name) {
            case "id" -> value = type.getId();
            case "name" -> value = type.getName();
            case "description" -> value = type.getDescription();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Type();
        }
        Type type = (Type) model;
        switch (name) {
            case "id" -> type.setId((int) value);
            case "name" -> type.setName((String) value);
            case "description" -> type.setDescription((String) value);
        }
        return model;
    }
}

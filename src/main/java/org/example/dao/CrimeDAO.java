package org.example.dao;

import org.example.model.Model;
import org.example.model.Crime;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrimeDAO extends DAO {
    @Override
    protected String getTable() {
        return "crime";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"type_id", "description"};
    }

    @Override
    public String[] getUpdateTypes() {
        return new String[]{"int", "string"};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{"id"};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Crime(
                rs.getInt("id"),
                rs.getInt("type_id"),
                rs.getString("description")
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Crime crime = (Crime) model;
        Object value = null;
        switch (name) {
            case "id" -> value = crime.getId();
            case "type_id" -> value = crime.getTypeId();
            case "description" -> value = crime.getDescription();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Crime();
        }
        Crime crime = (Crime) model;
        switch (name) {
            case "id" -> crime.setId((int) value);
            case "type_id" -> crime.setTypeId((int) value);
            case "description" -> crime.setDescription((String) value);
        }
        return model;
    }
}

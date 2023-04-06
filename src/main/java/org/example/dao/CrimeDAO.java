package org.example.dao;

import org.example.model.Model;
import org.example.model.Crime;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrimeDAO extends DAO {
    private final String ID = "id";
    private final String TYPE_ID = "type_id";
    private final String DESCRIPTION = "description";

    @Override
    protected String getTable() {
        return "crime";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{TYPE_ID, DESCRIPTION};
    }

    @Override
    public DataType[] getUpdateTypes() {
        return new DataType[]{DataType.INT, DataType.STRING};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{ID};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Crime(
                rs.getInt(ID),
                rs.getInt(TYPE_ID),
                rs.getString(DESCRIPTION)
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Crime crime = (Crime) model;
        Object value;
        switch (name) {
            case ID -> value = crime.getId();
            case TYPE_ID -> value = crime.getTypeId();
            case DESCRIPTION -> value = crime.getDescription();
            default -> throw new IllegalArgumentException();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Crime();
        }
        Crime crime = (Crime) model;
        switch (name) {
            case ID -> crime.setId((int) value);
            case TYPE_ID -> crime.setTypeId((int) value);
            case DESCRIPTION -> crime.setDescription((String) value);
            default -> throw new IllegalArgumentException();
        }
        return model;
    }
}

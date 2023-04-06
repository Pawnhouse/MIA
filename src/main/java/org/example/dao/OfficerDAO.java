package org.example.dao;

import org.example.model.Model;
import org.example.model.Officer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficerDAO extends DAO {
    private final String ID = "id";
    private final String PERSON_ID = "person_id";
    private final String BOSS_ID = "boss_id";
    private final String DEPARTMENT_ID = "department_id";

    @Override
    protected String getTable() {
        return "officer";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{PERSON_ID, BOSS_ID, DEPARTMENT_ID};
    }

    @Override
    public DataType[] getUpdateTypes() {
        return new DataType[]{DataType.INT, DataType.INT, DataType.INT};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{ID};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Officer(
                rs.getInt(ID),
                rs.getInt(PERSON_ID),
                rs.getInt(BOSS_ID),
                rs.getInt(DEPARTMENT_ID)
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Officer officer = (Officer) model;
        Object value;
        switch (name) {
            case ID -> value = officer.getId();
            case PERSON_ID -> value = officer.getPersonId();
            case BOSS_ID -> value = officer.getBossId();
            case DEPARTMENT_ID -> value = officer.getDepartmentId();
            default -> throw new IllegalArgumentException();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Officer();
        }
        Officer officer = (Officer) model;
        switch (name) {
            case ID -> officer.setId((int) value);
            case PERSON_ID -> officer.setPersonId((int) value);
            case BOSS_ID -> officer.setBossId((Integer) value);
            case DEPARTMENT_ID -> officer.setDepartmentId((int) value);
            default -> throw new IllegalArgumentException();
        }
        return model;
    }
}

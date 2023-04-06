package org.example.dao;

import org.example.model.Model;
import org.example.model.Criminal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CriminalDAO extends DAO {
    private final String ID = "id";
    private final String PERSON_ID = "person_id";

    private final String NICKNAME = "nickname";
    private final String BIOGRAPHY = "biography";

    @Override
    protected String getTable() {
        return "criminal";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{PERSON_ID, NICKNAME, BIOGRAPHY};
    }

    @Override
    public DataType[] getUpdateTypes() {
        return new DataType[]{DataType.INT, DataType.STRING, DataType.STRING};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{ID};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Criminal(
                rs.getInt(ID),
                rs.getInt(PERSON_ID),
                rs.getString(NICKNAME),
                rs.getString(BIOGRAPHY)
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Criminal criminal = (Criminal) model;
        Object value;
        switch (name) {
            case ID -> value = criminal.getId();
            case PERSON_ID -> value = criminal.getPersonId();
            case NICKNAME -> value = criminal.getNickname();
            case BIOGRAPHY -> value = criminal.getBiography();
            default -> throw new IllegalArgumentException();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Criminal();
        }
        Criminal criminal = (Criminal) model;
        switch (name) {
            case ID -> criminal.setId((int) value);
            case PERSON_ID -> criminal.setPersonId((int) value);
            case NICKNAME -> criminal.setNickname((String) value);
            case BIOGRAPHY -> criminal.setBiography((String) value);
            default -> throw new IllegalArgumentException();
        }
        return model;
    }
}

package org.example.dao;

import org.example.model.Model;
import org.example.model.CrimeCriminal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrimeCriminalDAO extends DAO {
    private final String CRIME_ID = "crime_id";
    private final String CRIMINAL_ID = "criminal_id";
    @Override
    protected String getTable() {
        return "crime_criminal";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{CRIME_ID, CRIMINAL_ID};
    }

    @Override
    public DataType[] getUpdateTypes() {
        return new DataType[]{DataType.INT, DataType.INT};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{CRIME_ID, CRIMINAL_ID};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new CrimeCriminal(
                rs.getInt(CRIME_ID),
                rs.getInt(CRIMINAL_ID)
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        CrimeCriminal crimeCriminal = (CrimeCriminal) model;
        Object value;
        switch (name) {
            case CRIME_ID -> value = crimeCriminal.getCrimeId();
            case CRIMINAL_ID -> value = crimeCriminal.getCriminalId();
            default -> throw new IllegalArgumentException();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new CrimeCriminal();
        }
        CrimeCriminal crimeCriminal = (CrimeCriminal) model;
        switch (name) {
            case CRIME_ID -> crimeCriminal.setCrimeId((int) value);
            case CRIMINAL_ID -> crimeCriminal.setCriminalId((int) value);
            default -> throw new IllegalArgumentException();
        }
        return model;
    }
}

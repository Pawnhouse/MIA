package org.example.dao;

import org.example.model.Model;
import org.example.model.CrimeCriminal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CrimeCriminalDAO extends DAO {
    @Override
    protected String getTable() {
        return "crime_criminal";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"crime_id", "criminal_id"};
    }

    @Override
    public String[] getUpdateTypes() {
        return new String[]{"int", "int"};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{"crime_id", "criminal_id"};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new CrimeCriminal(
                rs.getInt("crime_id"),
                rs.getInt("criminal_id")
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        CrimeCriminal crimeCriminal = (CrimeCriminal) model;
        Object value = null;
        switch (name) {
            case "crime_id" -> value = crimeCriminal.getCrimeId();
            case "criminal_id" -> value = crimeCriminal.getCriminalId();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new CrimeCriminal();
        }
        CrimeCriminal crimeCriminal = (CrimeCriminal) model;
        switch (name) {
            case "crime_id" -> crimeCriminal.setCrimeId((int) value);
            case "criminal_id" -> crimeCriminal.setCriminalId((int) value);
        }
        return model;
    }
}

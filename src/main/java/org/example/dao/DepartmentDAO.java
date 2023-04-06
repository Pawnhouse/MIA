package org.example.dao;

import org.example.model.Department;
import org.example.model.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO extends DAO {
    private final String ID = "id";
    private final String NAME = "name";
    private final String ADDRESS = "address";

    @Override
    protected String getTable() {
        return "department";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{NAME, ADDRESS};
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
        return new Department(
                rs.getInt(ID),
                rs.getString(NAME),
                rs.getString(ADDRESS)
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Department department = (Department) model;
        Object value;
        switch (name) {
            case ID -> value = department.getId();
            case NAME -> value = department.getName();
            case ADDRESS -> value = department.getAddress();
            default -> throw new IllegalArgumentException();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Department();
        }
        Department department = (Department) model;
        switch (name) {
            case ID -> department.setId((int) value);
            case NAME -> department.setName((String) value);
            case ADDRESS -> department.setAddress((String) value);
            default -> throw new IllegalArgumentException();
        }
        return model;
    }
}

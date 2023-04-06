package org.example.dao;

import org.example.model.Department;
import org.example.model.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO extends DAO {
    @Override
    protected String getTable() {
        return "department";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"name", "address"};
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
        return new Department(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address")
        );
    }

    @Override
    protected Object getValue(Model model, String name) {
        Department department = (Department) model;
        Object value = null;
        switch (name) {
            case "id" -> value = department.getId();
            case "name" -> value = department.getName();
            case "address" -> value = department.getAddress();
        }
        return value;
    }

    public Model setValue(Model model, String name, Object value) {
        if (model == null) {
            model = new Department();
        }
        Department department = (Department) model;
        switch (name) {
            case "id" -> department.setId((int) value);
            case "name" -> department.setName((String) value);
            case "address" -> department.setAddress((String) value);
        }
        return model;
    }
}

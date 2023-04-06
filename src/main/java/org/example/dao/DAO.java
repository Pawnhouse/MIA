package org.example.dao;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.model.Model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public abstract class DAO {

    abstract protected String getTable();

    public abstract String[] getUpdateTypes();

    abstract public String[] getUpdateColumns();

    abstract public String[] getFindColumns();

    abstract protected Model creteObject(ResultSet rs) throws SQLException;

    abstract protected Object getValue(Model model, String name);

    abstract public Model setValue(Model model, String name, Object value);

    protected Connection connect() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("URL");
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", dotenv.get("PASSWORD"));
        props.setProperty("options", "-c lc_messages=en_US.UTF8 -c client_encoding=UTF8");
        return DriverManager.getConnection(url, props);

    }

    public List<Model> read() throws SQLException {
        try (Connection connection = this.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + getTable())) {
            List<Model> list = new ArrayList<>();
            while (rs.next()) {
                list.add(creteObject(rs));
            }
            return list;
        }
    }

    public Model read(Model model) throws SQLException {
        String[] idColumns = this.getFindColumns();
        String query = "SELECT * FROM " + getTable() + generateFindColumns();
        try (Connection connection = this.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < idColumns.length; i++) {
                setStatementValue(stmt, i + 1, "int", model, idColumns[i]);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return creteObject(rs);
            }
            return null;
        }
    }

    private void setStatementValue(PreparedStatement statement, int parameterIndex, String type, Model model, String name) throws SQLException {
        Object value = getValue(model, name);
        switch (type) {
            case "int" -> statement.setInt(parameterIndex, (int) value);
            case "string" -> statement.setString(parameterIndex, (String) value);
            case "date" -> statement.setDate(parameterIndex, (Date) value);
            case "timestamp" -> statement.setTimestamp(parameterIndex, (Timestamp) value);
        }
    }

    private String generateFindColumns() {
        String[] idColumns = this.getFindColumns();

        StringBuilder findColumns = new StringBuilder(" WHERE ");

        for (String column : idColumns) {
            findColumns.append(column).append(" = ? AND ");
        }
        findColumns.delete(findColumns.length() - 4, findColumns.length() - 1);
        return findColumns.toString();
    }

    private void fillStatement(PreparedStatement statement, Model newModel, Model oldModel) throws SQLException {
        String[] idColumns = this.getFindColumns();
        String[] columns = this.getUpdateColumns();
        String[] types = this.getUpdateTypes();
        if (newModel == null) {
            columns = new String[0];
        }
        if (oldModel == null) {
            idColumns = new String[0];
        }

        for (int i = 0; i < idColumns.length; i++) {
            setStatementValue(statement, i + 1 + columns.length, "int", oldModel, idColumns[i]);
        }
        for (int i = 0; i < columns.length; i++) {
            setStatementValue(statement, i + 1, types[i], newModel, columns[i]);
        }
    }

    private int executeChangeQuery(String query, Model newModel, Model oldModel) {
        try (Connection connection = this.connect();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            fillStatement(statement, newModel, oldModel);
            int affected = statement.executeUpdate();
            if (oldModel != null) {
                return affected;
            }

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return -1;
        } catch (Exception e) {
            //e.printStackTrace();
            if (e.getMessage() != null) {
                System.out.println("Error has happened");
            }
            return -1;
        }
    }

    public int create(Model model) {
        String[] columns = this.getUpdateColumns();
        StringBuilder questions = new StringBuilder(new String(new char[columns.length]).replace("\0", "?,"));
        questions.deleteCharAt(questions.length() - 1);
        String query = "INSERT INTO " + this.getTable() + "( " + String.join(", ", columns) + ") "
                + "VALUES(" + questions + ")";
        return executeChangeQuery(query, model, null);

    }

    public int update(Model newModel, Model oldModel) {
        String[] columns = this.getUpdateColumns();
        StringBuilder setColumns = new StringBuilder();

        for (String column : columns) {
            setColumns.append(column).append(" = ?, ");
        }
        setColumns.deleteCharAt(setColumns.length() - 2);
        String query = "UPDATE " + getTable() + " SET " + setColumns + generateFindColumns();

        return executeChangeQuery(query, newModel, oldModel);
    }

    public int delete(Model model) {
        String query = "DELETE FROM " + this.getTable() + generateFindColumns();
        return executeChangeQuery(query, null, model);
    }
}

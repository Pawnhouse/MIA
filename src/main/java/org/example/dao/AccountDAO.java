package org.example.dao;

import org.example.model.Model;
import org.example.model.Account;

import java.sql.*;

public class AccountDAO extends DAO {
    @Override
    protected String getTable() {
        return "account";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"person_id", "email", "password", "role"};
    }

    @Override
    public String[] getUpdateTypes() {
        return new String[]{"int", "string", "string", "string"};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{"id"};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Account(
                rs.getInt("id"),
                rs.getInt("person_id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
        );
    }

    @Override
    protected Object getValue(Model model, String person_id) {
        Account account = (Account) model;
        Object value = null;
        switch (person_id) {
            case "id" -> value = account.getId();
            case "person_id" -> value = account.getPersonId();
            case "email" -> value = account.getEmail();
            case "password" -> value = account.getPassword();
            case "role" -> value = account.getRole();
        }
        return value;
    }

    public Model setValue(Model model, String person_id, Object value) {
        if (model == null) {
            model = new Account();
        }
        Account account = (Account) model;
        switch (person_id) {
            case "id" -> account.setId((int) value);
            case "person_id" -> account.setPersonId((int) value);
            case "email" -> account.setEmail((String) value);
            case "password" -> account.setPassword((String) value);
            case "role" -> account.setRole((String) value);
        }
        return model;
    }

    public Boolean isEmailNotValid(String email) {
        String query = "SELECT count(*) exists FROM account WHERE email = ?";
        try (Connection connection = this.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            throw new Exception();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    public Account register(int person_id, String email, String password) {
        Account account = new Account(0, person_id, email, password, null);
        int id = create(account);
        if (id > 0) {
            account.setId(id);
            return account;
        }
        return null;
    }

    public Account login(String email, String password) {
        String query = "SELECT * FROM account WHERE email = ? AND password = ?";
        try (Connection connection = this.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (Account) creteObject(rs);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

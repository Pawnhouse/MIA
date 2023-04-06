package org.example.dao;

import org.example.model.Model;
import org.example.model.Account;

import java.sql.*;


public class AccountDAO extends DAO {
    private final String ID = "id";
    private final String PASSWORD = "password";
    private final String EMAIL = "email";
    private final String PERSON_ID = "person_id";
    private final String ROLE = "role";
    @Override
    protected String getTable() {
        return "account";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{PERSON_ID, EMAIL, PASSWORD, ROLE};
    }

    @Override
    public DataType[] getUpdateTypes() {
        return new DataType[]{DataType.INT, DataType.STRING, DataType.STRING, DataType.STRING};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{ID};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Account(
                rs.getInt(ID),
                rs.getInt(PERSON_ID),
                rs.getString(EMAIL),
                rs.getString(PASSWORD),
                rs.getString(ROLE)
        );
    }

    @Override
    protected Object getValue(Model model, String person_id) {
        Account account = (Account) model;
        Object value;
        switch (person_id) {
            case ID -> value = account.getId();
            case PERSON_ID -> value = account.getPersonId();
            case EMAIL -> value = account.getEmail();
            case PASSWORD -> value = account.getPassword();
            case ROLE -> value = account.getRole();
            default -> throw new IllegalArgumentException();

        }
        return value;
    }

    public Model setValue(Model model, String person_id, Object value) {
        if (model == null) {
            model = new Account();
        }
        Account account = (Account) model;
        switch (person_id) {
            case ID -> account.setId((int) value);
            case PERSON_ID -> account.setPersonId((int) value);
            case EMAIL -> account.setEmail((String) value);
            case PASSWORD -> account.setPassword((String) value);
            case ROLE -> account.setRole((String) value);
            default -> throw new IllegalArgumentException();

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

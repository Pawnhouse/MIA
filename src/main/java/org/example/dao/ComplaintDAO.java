package org.example.dao;

import org.example.model.Model;
import org.example.model.Complaint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ComplaintDAO extends DAO {
    @Override
    protected String getTable() {
        return "complaint";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{"sender_id", "description", "sent"};
    }

    @Override
    public String[] getUpdateTypes() {
        return new String[]{"int", "string", "timestamp"};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{"id"};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Complaint(
                rs.getInt("id"),
                rs.getInt("sender_id"),
                rs.getString("description"),
                rs.getTimestamp("sent")
        );
    }

    @Override
    protected Object getValue(Model model, String account_id) {
        Complaint complaint = (Complaint) model;
        Object value = null;
        switch (account_id) {
            case "id" -> value = complaint.getId();
            case "sender_id" -> value = complaint.getSenderId();
            case "description" -> value = complaint.getDescription();
            case "sent" -> value = complaint.getSent();
        }
        return value;
    }

    public Model setValue(Model model, String account_id, Object value) {
        if (model == null) {
            model = new Complaint();
        }
        Complaint complaint = (Complaint) model;
        switch (account_id) {
            case "id" -> complaint.setId((int) value);
            case "sender_id" -> complaint.setSenderId((int) value);
            case "description" -> complaint.setDescription((String) value);
            case "sent" -> complaint.setSent((Timestamp) value);
        }
        return model;
    }

    public Boolean sendComplaint(int accountId, String description) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Complaint complaint = new Complaint(0, accountId, description, timestamp);
        return create(complaint) > 0;
    }
}

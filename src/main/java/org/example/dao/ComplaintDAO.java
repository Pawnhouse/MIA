package org.example.dao;

import org.example.model.Model;
import org.example.model.Complaint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ComplaintDAO extends DAO {
    private final String ID = "id";
    private final String SENDER_ID = "sender_id";
    private final String DESCRIPTION = "description";
    private final String SENT = "sent";

    @Override
    protected String getTable() {
        return "complaint";
    }

    @Override
    public String[] getUpdateColumns() {
        return new String[]{SENDER_ID, DESCRIPTION, SENT};
    }

    @Override
    public DataType[] getUpdateTypes() {
        return new DataType[]{DataType.INT, DataType.STRING, DataType.TIMESTAMP};
    }

    @Override
    public String[] getFindColumns() {
        return new String[]{ID};
    }

    @Override
    protected Model creteObject(ResultSet rs) throws SQLException {
        return new Complaint(
                rs.getInt(ID),
                rs.getInt(SENDER_ID),
                rs.getString(DESCRIPTION),
                rs.getTimestamp(SENT)
        );
    }

    @Override
    protected Object getValue(Model model, String account_id) {
        Complaint complaint = (Complaint) model;
        Object value;
        switch (account_id) {
            case ID -> value = complaint.getId();
            case SENDER_ID -> value = complaint.getSenderId();
            case DESCRIPTION -> value = complaint.getDescription();
            case SENT -> value = complaint.getSent();
            default -> throw new IllegalArgumentException();
        }
        return value;
    }

    public Model setValue(Model model, String account_id, Object value) {
        if (model == null) {
            model = new Complaint();
        }
        Complaint complaint = (Complaint) model;
        switch (account_id) {
            case ID -> complaint.setId((int) value);
            case SENDER_ID -> complaint.setSenderId((int) value);
            case DESCRIPTION -> complaint.setDescription((String) value);
            case SENT -> complaint.setSent((Timestamp) value);
            default -> throw new IllegalArgumentException();
        }
        return model;
    }

    public Boolean sendComplaint(int accountId, String description) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Complaint complaint = new Complaint(0, accountId, description, timestamp);
        return create(complaint) > 0;
    }
}

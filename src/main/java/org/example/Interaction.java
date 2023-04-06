package org.example;

import org.example.dao.DAO;
import org.example.model.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Interaction {
    private static final String[] askFindStr = new String[]{"", "What is row to update?\n", "What is row to delete?\n"};
    private static final String[] askNewStr = new String[]{"Input values\n", "Input new values\n", ""};

    private static final Scanner in = Main.in;

    public static void read(DAO dao) {
        try {
            List<Model> list = dao.read();
            if (list.size() == 0) {
                System.out.println("Empty set");
            }
            for (Model obj : list) {
                obj.show();
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Message of Exception : " + e.getMessage());
        }
    }

    public static void readOne(DAO dao) {
        try {
            Model model = getOldModel(dao, 0);
            model = dao.read(model);
            if (model == null) {
                System.out.println("Empty set");
                return;
            }
            model.show();
            System.out.println();
        } catch (Exception e) {
            System.out.println("Message of Exception : " + e.getMessage());
        }
    }

    private static Model setValue(DAO dao, String type, String name, Model model) {
        System.out.printf("%s: ", name);
        Object value = null;
        if (type.equals("int")) {
            value = in.nextInt();
            in.nextLine();
        }
        if (type.equals("string")) {
            value = in.nextLine();
        }
        if (type.equals("date")) {
            String s = in.nextLine();
            value = Date.valueOf(s);
        }
        if (type.equals("timestamp")) {
            String s = in.nextLine();
            value = Timestamp.valueOf(s);
        }
        return dao.setValue(model, name, value);
    }

    public static Model getNewModel(DAO dao, int type) {
        String[] columns = dao.getUpdateColumns();
        String[] types = dao.getUpdateTypes();
        Model model = null;
        System.out.println(askNewStr[type]);
        for (int i = 0; i < columns.length; i++) {
            model = setValue(dao, types[i], columns[i], model);
        }
        return model;
    }

    public static Model getOldModel(DAO dao, int type) {
        String[] idColumns = dao.getFindColumns();
        Model model = null;
        System.out.print(askFindStr[type]);
        for (String idColumn : idColumns) {
            model = setValue(dao, "int", idColumn, model);
        }
        return model;
    }

    public static void modify(DAO dao, int type) {
        try {
            int affectedRows = -1;
            switch (type) {
                case 0 -> affectedRows = dao.create(getNewModel(dao, type));
                case 1 -> affectedRows = dao.update(getNewModel(dao, type), getOldModel(dao, type));
                case 2 -> affectedRows = dao.delete(getOldModel(dao, type));
            }
            if (affectedRows <= 0) {
                System.out.println("Nothing has changed");
            } else {
                System.out.println("Success");
            }

        } catch (InputMismatchException e) {
            System.out.println("This is not a number");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

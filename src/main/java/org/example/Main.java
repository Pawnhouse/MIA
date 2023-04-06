package org.example;

import org.example.dao.*;
import org.example.model.*;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final Scanner in = new Scanner(System.in);
    private static Boolean isAuthorized = false;
    private static Account account = null;

    private static void cycleCitizen() {
        System.out.println(
                """
                        Commands:
                        s       -   Send complaint
                        exit    -   Exits from program
                        """
        );
        while (true) {
            String command = in.nextLine();
            if (command.equals("s")) {
                System.out.println("Description:");
                String description = in.nextLine();
                ComplaintDAO complaintDAO = new ComplaintDAO();
                boolean success = complaintDAO.sendComplaint(account.getId(), description);
                if (success) {
                    System.out.println("Complaint has been sent");
                } else {
                    System.out.println("Error. Complaint hasn't been sent");
                }
            }
            if (command.equals("exit")) {
                break;
            }
        }
    }

    private static void cycleOfficer() {
        System.out.println(
                """
                        Commands:
                        c table         -   Creates new row in a table
                        r table [--one] -   Reads all rows or one with "--one" option from a table
                        u table         -   Updates row in a table
                        d table         -   Deletes row from a table
                        exit            -   Exits from program
                        """
        );
        while (true) {
            String command = in.nextLine();
            runQuery(command);
            if (command.equals("exit")) {
                break;
            }

        }

    }

    private static void runQuery(String command) {
        DAO dao;
        String[] args = command.split(" ");
        if (args.length == 1) {
            return;
        }
        switch (args[1]) {
            case "type" -> dao = new TypeDAO();
            case "person" -> dao = new PersonDAO();
            case "department" -> dao = new DepartmentDAO();
            case "officer" -> dao = new OfficerDAO();
            case "criminal" -> dao = new CriminalDAO();
            case "crime_criminal" -> dao = new CrimeCriminalDAO();
            case "crime" -> dao = new CrimeDAO();
            case "account" -> dao = new AccountDAO();
            case "complaint" -> dao = new ComplaintDAO();
            default ->{
                System.out.println("Wrong table");
                return;
            }
        }
        if (args.length == 3 && args[0].equals("r") && args[2].equals("--one")) {
            Interaction.readOne(dao);
            return;
        }
        if (args[1].equals("account") && !args[0].equals("r")) {
            System.out.println("You can only read table account");
        }
        switch (args[0]) {
            case "c" -> Interaction.modify(dao, 0);
            case "r" -> Interaction.read(dao);
            case "u" -> Interaction.modify(dao, 1);
            case "d" -> Interaction.modify(dao, 2);
            default -> { } // other options
        }
    }

    private static void signUp() {
        PersonDAO personDAO = new PersonDAO();
        AccountDAO accountDAO = new AccountDAO();
        System.out.print("Email: ");
        String email = in.nextLine();
        if (accountDAO.isEmailNotValid(email)) {
            System.out.println("Account already exists");
            return;
        }
        System.out.print("Password: ");
        String password = in.nextLine();
        System.out.print("Repeat: ");
        String repeatPassword = in.nextLine();
        if (!password.equals(repeatPassword)) {
            System.out.println("Passwords don't match");
            return;
        }
        Model person = Interaction.getNewModel(personDAO, 0);
        int person_id = personDAO.create(person);
        account = accountDAO.register(person_id, email, password);
        if (account == null) {
            System.out.println("Error has happened");
            return;
        }
        isAuthorized = true;
    }

    private static void signIn() {
        AccountDAO accountDAO = new AccountDAO();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();
        account = accountDAO.login(email, password);
        if (account == null) {
            System.out.println("Wrong password or email");
            return;
        }
        isAuthorized = true;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("input: 1 - Sign up; 2 - Sign in");
        String command = in.nextLine();
        if (command.equals("1") || command.equals("Sign up")) {
            signUp();
        }
        if (command.equals("2") || command.equals("Sign in")) {
            signIn();
        }

        if (isAuthorized) {
            if (account.getRole() != null) {
                cycleOfficer();
            } else {
                cycleCitizen();
            }
        }
        in.close();
    }
}
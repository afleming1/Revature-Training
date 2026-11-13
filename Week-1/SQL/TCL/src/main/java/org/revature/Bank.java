package org.revature;

import java.sql.*;

public class Bank {
    public static void main(String[] args) throws SQLException {
        try {
            setupBank();
            checkAccounts();
            //badTransferAutoCommitting();
            badTransferManualCommitting();
            checkAccounts();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            checkAccounts();
        }
    }

    public static void setupBank() throws SQLException {
        String resetSQL = "DROP TABLE IF EXISTS accounts";

        String sql = "CREATE TABLE accounts(id INTEGER PRIMARY KEY, balance INTEGER CHECK (balance >= 0))";
        String sql2 = "INSERT INTO accounts (balance) VALUES (100), (100)";

        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(resetSQL);
            statement.execute(sql);
            statement.execute(sql2);
        }
    }

    public static void checkAccounts() throws SQLException {
        String sql = "SELECT * FROM accounts";

        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int balance = resultSet.getInt("balance");

                Account account = new Account(id, balance);

                System.out.println(account);
            }
        }
    }

    public static void badTransferAutoCommitting() throws SQLException {
        // This is a bad transfer - account 11 doesn't exist, so account 2 should not lose any money
        try (Connection connection = ConnectionFactory.getConnection()) {
            String transferFrom = "UPDATE accounts SET balance = 0 WHERE id = 2";
            String transferTo = "UPDATE accounts SET balance = 200 WHERE id = 11";

            Statement statement = connection.createStatement();

            int rowsAffected = statement.executeUpdate(transferFrom);

            if (rowsAffected != 1) {
                throw new RuntimeException("Transferring money from account failed, need to cancel whole transaction");
            }

            rowsAffected = statement.executeUpdate(transferTo);

            if (rowsAffected != 1) {
                throw new RuntimeException("Money not received, need to cancel whole transaction");
            }
        }
    }

    public static void badTransferManualCommitting() throws SQLException {
        try (Connection connection = ConnectionFactory.getManualCommitConnection()) {
            String transferFrom = "UPDATE accounts SET balance = 0 WHERE id = 2";
            String transferTo = "UPDATE accounts SET balance = 200 WHERE id = 1";

            Statement statement = connection.createStatement();

            // Make savepoint
            Savepoint savepoint = connection.setSavepoint("Start of transaction");

            int rowsAffected = statement.executeUpdate(transferFrom);

            if (rowsAffected != 1) {
                // Rollback to savepoint
                connection.rollback(savepoint);

                throw new RuntimeException("Transferring money from account failed, need to cancel whole transaction");
            }

            rowsAffected = statement.executeUpdate(transferTo);

            if (rowsAffected != 1) {
                // Rollback entire transaction
                connection.rollback();

                throw new RuntimeException("Money not received, need to cancel whole transaction");
            }

            // Commit entire transaction if everything went well
            connection.commit();
        }
    }
}

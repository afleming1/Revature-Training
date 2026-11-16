package dev.afleming.problems;

import dev.afleming.ConnectionFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLProblems {
    public static void executeSql() throws IOException, SQLException {
        String fileContent = Files.readString(Path.of("src/main/resources/sql/SQLProblems.sql"));
        String[] commands = fileContent.split(";");

        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement statement = connection.createStatement();

            for (String command : commands) {
                String trimmedCommand = command.trim();

                if (trimmedCommand.isEmpty()) {
                    continue;
                }

                boolean hasResultSet = statement.execute(trimmedCommand);

                if (hasResultSet) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        while (resultSet.next()) {
                            System.out.printf("%s%n", resultSet.getString(1));
                        }
                    }
                }
            }
        }
    }
}

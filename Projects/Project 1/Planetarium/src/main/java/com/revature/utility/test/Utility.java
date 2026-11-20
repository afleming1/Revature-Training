package com.revature.utility.test;

import com.revature.utility.DatabaseConnector;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Objects;
import java.util.stream.Stream;

public class Utility {
    public static void main(String[] args) throws IOException {
        resetTestDatabase();
    }

    public static void resetTestDatabase() throws IOException {
        ClassLoader classLoader = Utility.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("Setup-Reset.sql");
        StringBuilder sqlBuilder = new StringBuilder();

        try (Connection connection = DatabaseConnector.getConnection(); Stream<String> lines = (new BufferedReader(new InputStreamReader(inputStream)).lines());) {
            connection.setAutoCommit(false);

            Objects.requireNonNull(sqlBuilder);

            lines.forEach(sqlBuilder::append);

            String[] sqlStatements = sqlBuilder.toString().split(";");

            int imageCount = 1;

            for (String sqlStatement : sqlStatements) {
                String sql = sqlStatement.trim();

                if (!sql.isEmpty()) {
                    if (sql.contains("?")) {
                        String type = sql.contains("moons") ? "moon" : "planet";

                        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                            byte[] imageData = classLoader.getResourceAsStream(String.format("Celestial-Images/%s-%d.jpg", type, imageCount)).readAllBytes();

                            preparedStatement.setBytes(1, imageData);
                            preparedStatement.executeUpdate();

                            imageCount = imageCount == 2 ? 1 : 2;
                        }
                    } else {
                        try (Statement statement = connection.createStatement()) {
                            statement.executeUpdate(sql);
                        }
                    }
                }
            }

            connection.commit();
        } catch (SQLException | IOException exception) {
            ((Exception) exception).printStackTrace();
        }
    }

    public static String convertToBase64(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] fileBytes = fileInputStream.readAllBytes();

            return Base64.getEncoder().encodeToString(fileBytes);
        }
    }

    public static byte[] convertImgToByteArray(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}

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

        try (InputStream setupScript = classLoader.getResourceAsStream("Setup-Reset.sql"); Connection connection = DatabaseConnector.getConnection()) {
            if (setupScript == null) {
                throw new FileNotFoundException("Missing resource: Setup-Reset.sql");
            }

            StringBuilder sqlBuilder = new StringBuilder();

            try (Stream<String> lines = new BufferedReader(new InputStreamReader(setupScript)).lines()) {
                lines.forEach(sqlBuilder::append);
            }

            connection.setAutoCommit(false);

            String[] sqlStatements = sqlBuilder.toString().split(";");
            int imageCount = 1;

            for (String sqlStatement : sqlStatements) {
                String sql = sqlStatement.trim();

                if (sql.isEmpty()) {
                    continue;
                }

                if (sql.contains("?")) {
                    String type = sql.contains("moons") ? "moon" : "planet";
                    String imagePath = String.format("Celestial-Images/%s-%d.jpg", type, imageCount);

                    try (InputStream imageStream = classLoader.getResourceAsStream(imagePath); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        if (imageStream == null) {
                            throw new FileNotFoundException("Missing resource: " + imagePath);
                        }

                        preparedStatement.setBytes(1, imageStream.readAllBytes());
                        preparedStatement.executeUpdate();

                        imageCount = imageCount == 2 ? 1 : 2;
                    }
                } else {
                    try (Statement statement = connection.createStatement()) {
                        statement.executeUpdate(sql);
                    }
                }
            }

            connection.commit();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();

            if (exception instanceof IOException ioException) {
                throw ioException;
            }
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

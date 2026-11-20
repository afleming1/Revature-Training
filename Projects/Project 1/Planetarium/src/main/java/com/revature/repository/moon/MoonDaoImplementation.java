package com.revature.repository.moon;

import com.revature.entities.Moon;
import com.revature.utility.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MoonDaoImplementation implements MoonDao {
    public boolean createMoon(Moon moon) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO moons (name, planetId, image) VALUES (?, ?, ?)", 1)) {
                statement.setString(1, moon.getMoonName());
                statement.setInt(2, moon.getOwnerId());
                statement.setBytes(3, moon.imageDataAsByteArray());

                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int newMoonId = resultSet.getInt(1);
                        moon.setMoonId(newMoonId);

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Moon readMoon(String moonName) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM moons WHERE name = ?")) {
                statement.setString(1, moonName);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Moon moon = new Moon();
                    moon.setMoonId(resultSet.getInt("id"));
                    moon.setMoonName(resultSet.getString("name"));
                    moon.setOwnerId(resultSet.getInt("myPlanetId"));

                    byte[] byteImageData = resultSet.getBytes("image");

                    if (byteImageData != null) {
                        String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);

                        moon.setImageData(base64ImageData);
                    }

                    return moon;
                }

                return null;
            }
        }
    }

    public List<Moon> readMoonsByPlanet(int planetId) throws SQLException {
        List<Moon> moons = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM moons WHERE myPlanetId = ?")) {
                statement.setInt(1, planetId);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Moon moon = new Moon();
                    moon.setMoonId(resultSet.getInt("id"));
                    moon.setMoonName(resultSet.getString("name"));
                    moon.setOwnerId(resultSet.getInt("ownerId"));

                    byte[] byteImageData = resultSet.getBytes("image");

                    if (byteImageData != null) {
                        String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);

                        moon.setImageData(base64ImageData);

                        moons.add(moon);
                    }
                }
            }
        }

        return moons;
    }

    public boolean deleteMoon(String moonName) throws SQLException {
        boolean deleted;

        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM moons WHERE name = ?")) {
                statement.setString(1, moonName);

                int rowsDeleted = statement.executeUpdate();

                deleted = rowsDeleted > 0;
            }
        }

        return deleted;
    }
}

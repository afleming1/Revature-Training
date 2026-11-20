package com.revature.repository.planet;

import com.revature.entities.Planet;
import com.revature.utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PlanetDaoImplementation implements PlanetDao {
    public boolean createPlanet(Planet planet) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO planets (name, ownerId, image) VALUES (?, ?, ?)", 1)) {
                statement.setString(1, planet.getPlanetName());
                statement.setInt(2, planet.getOwnerId());
                statement.setBytes(3, planet.imageDataAsByteArray());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int newPlanetId = resultSet.getInt(1);
                        planet.setPlanetId(newPlanetId);

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Planet readPlanet(String planetName) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM planets WHERE name = ?")) {
                statement.setString(1, planetName);

                ResultSet resultSet = statement.executeQuery();
                Planet planet = null;

                if (resultSet.next()) {
                    planet = new Planet();
                    planet.setPlanetId(resultSet.getInt("id"));
                    planet.setPlanetName(resultSet.getString("name"));
                    planet.setOwnerId(resultSet.getInt("ownerId"));

                    byte[] byteImageData = resultSet.getBytes("image");

                    if (byteImageData != null) {
                        String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);

                        planet.setImageData(base64ImageData);
                    }

                    return planet;
                }

                return null;
            }
        }
    }

    public List<Planet> readPlanetsByOwner(int userId) throws SQLException {
        List<Planet> planets = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM planets WHERE ownerId = ?")) {
                statement.setInt(1, userId);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Planet planet = new Planet();
                    planet.setPlanetId(resultSet.getInt("id"));
                    planet.setPlanetName(resultSet.getString("name"));
                    planet.setOwnerId(resultSet.getInt("ownerId"));

                    byte[] byteImageData = resultSet.getBytes("image");

                    if (byteImageData != null) {
                        String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);

                        planet.setImageData(base64ImageData);

                        planets.add(planet);
                    }
                }
            }
        }

        return planets;
    }

    public boolean deletePlanet(String planetName) throws SQLException {
        boolean deleted;

        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM planets WHERE name = ?")) {
                statement.setString(1, planetName);

                int rowsDeleted = statement.executeUpdate();

                deleted = rowsDeleted > 0;
            }
        }

        return deleted;
    }
}

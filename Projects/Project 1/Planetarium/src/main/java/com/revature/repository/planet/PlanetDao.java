package com.revature.repository.planet;

import com.revature.entities.Planet;

import java.sql.SQLException;
import java.util.List;

public interface PlanetDao {
    boolean createPlanet(Planet planet) throws SQLException;

    Planet readPlanet(String planetName) throws SQLException;

    List<Planet> readPlanetsByOwner(int userId) throws SQLException;

    boolean deletePlanet(String planetName) throws SQLException;
}

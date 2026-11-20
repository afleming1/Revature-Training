package com.revature.repository.moon;

import com.revature.entities.Moon;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MoonDao {
    boolean createMoon(Moon moon) throws SQLException;

    Moon readMoon(String moonName) throws SQLException;

    List<Moon> readMoonsByPlanet(int planetId) throws SQLException;

    boolean deleteMoon(String moonName) throws SQLException;
}

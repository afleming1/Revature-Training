package com.revature.service.moon;

import com.revature.entities.Moon;

import java.util.List;

public interface MoonService {
    boolean createMoon(Moon moon);

    List<Moon> selectByPlanetId(int planetId);

    boolean deleteMoon(String moonName);
}

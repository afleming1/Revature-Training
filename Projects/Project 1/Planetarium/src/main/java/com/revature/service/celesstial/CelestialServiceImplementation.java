package com.revature.service.celesstial;

import com.revature.entities.Moon;
import com.revature.entities.Planet;
import com.revature.service.moon.MoonService;
import com.revature.service.planet.PlanetService;

import java.util.ArrayList;
import java.util.List;

public class CelestialServiceImplementation implements CelestialService {
    private MoonService moonService;
    private PlanetService planetService;

    public CelestialServiceImplementation(MoonService moonService, PlanetService planetService) {
        this.moonService = moonService;
        this.planetService = planetService;
    }

    public List<Moon> getUserMoons(int userId) {
        List<Planet> userPlanets = this.planetService.selectByOwner(userId);
        List<Moon> userMoons = new ArrayList<>();

        for (Planet planet : userPlanets) {
            userMoons.addAll(this.moonService.selectByPlanetId(planet.getPlanetId()));
        }

        return userMoons;
    }
}

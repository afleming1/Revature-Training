package com.revature.controller;

import com.revature.entities.Planet;
import com.revature.service.planet.PlanetService;
import io.javalin.http.Context;

import java.util.List;

public class PlanetController {
    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    public void findAllByOwner(Context ctx) {
        int ownerId = Integer.parseInt(ctx.pathParam("ownerId"));

        List<Planet> planets = this.planetService.selectByOwner(ownerId);

        ctx.status(200).json(planets);
    }

    public void createPlanet(Context ctx) {
        Planet planet = ctx.bodyAsClass(Planet.class);

        int userId = Integer.parseInt(ctx.pathParam("ownerId"));

        boolean planetCreated = this.planetService.createPlanet(userId, planet);

        if (planetCreated) {
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public void deletePlanet(Context ctx) {
        String identifier = ctx.pathParam("identifier");

        int userId = Integer.parseInt(ctx.pathParam("ownerId"));

        this.planetService.deletePlanet(userId, identifier);

        ctx.status(204);
    }
}

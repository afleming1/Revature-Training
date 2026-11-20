package com.revature.controller;

import com.revature.entities.Moon;
import com.revature.service.celesstial.CelestialService;
import io.javalin.http.Context;

import java.util.List;

public class CelestialController {
    private CelestialService celestialService;

    public CelestialController(CelestialService celestialService) {
        this.celestialService = celestialService;
    }

    public void findUserMoons(Context ctx) {
        int userId = Integer.parseInt(ctx.pathParam("userId"));

        List<Moon> moons = this.celestialService.getUserMoons(userId);

        ctx.status(200).json(moons);
    }
}

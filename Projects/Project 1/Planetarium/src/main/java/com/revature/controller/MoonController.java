package com.revature.controller;

import com.revature.entities.Moon;
import com.revature.service.moon.MoonService;
import io.javalin.http.Context;

public class MoonController {
    private MoonService moonService;

    public MoonController(MoonService moonService) {
        this.moonService = moonService;
    }

    public void createMoon(Context ctx) {
        Moon moon = ctx.bodyAsClass(Moon.class);

        this.moonService.createMoon(moon);

        ctx.status(201);
    }

    public void deleteMoon(Context ctx) {
        String identifier = ctx.pathParam("identifier");

        this.moonService.deleteMoon(identifier);

        ctx.status(204);
    }
}

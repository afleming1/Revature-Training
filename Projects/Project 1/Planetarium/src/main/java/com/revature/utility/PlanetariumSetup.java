package com.revature.utility;

import com.revature.controller.CelestialController;
import com.revature.controller.MoonController;
import com.revature.controller.PlanetController;
import com.revature.controller.UserController;
import com.revature.controller.ViewController;
import com.revature.exceptions.AuthenticationFailed;
import com.revature.exceptions.MoonFail;
import com.revature.exceptions.PlanetFail;
import com.revature.exceptions.UserFail;
import com.revature.repository.moon.MoonDao;
import com.revature.repository.moon.MoonDaoImplementation;
import com.revature.repository.planet.PlanetDao;
import com.revature.repository.planet.PlanetDaoImplementation;
import com.revature.repository.user.UserDao;
import com.revature.repository.user.UserDaoImplementation;
import com.revature.service.celesstial.CelestialService;
import com.revature.service.celesstial.CelestialServiceImplementation;
import com.revature.service.moon.MoonService;
import com.revature.service.moon.MoonServiceImplementation;
import com.revature.service.planet.PlanetService;
import com.revature.service.planet.PlanetServiceImplementation;
import com.revature.service.user.UserService;
import com.revature.service.user.UserServiceImplementation;
import com.revature.utility.test.Utility;
import io.javalin.Javalin;

public class PlanetariumSetup {
    private static final UserDao userDao = new UserDaoImplementation();
    private static final UserService userService = new UserServiceImplementation(userDao);
    private static final UserController userController = new UserController(userService);

    private static final PlanetDao planetDao = new PlanetDaoImplementation();
    private static final PlanetService planetService = new PlanetServiceImplementation(planetDao);
    private static final PlanetController planetController = new PlanetController(planetService);

    private static final MoonDao moonDao = new MoonDaoImplementation();
    private static final MoonService moonService = new MoonServiceImplementation(moonDao);
    private static final MoonController moonController = new MoonController(moonService);

    private static final CelestialService celestialService = new CelestialServiceImplementation(moonService, planetService);
    private static final CelestialController celestialController = new CelestialController(celestialService);

    private static final ViewController viewController = new ViewController();

    private PlanetariumSetup() {}

    public static void mapRoutes(Javalin app) {
        mapTestRoutes(app);
        mapAuthFilters(app);
        mapExceptionHandlers(app);
        mapViewRoutes(app);
        mapUserRoutes(app);
        mapPlanetRoutes(app);
        mapMoonRoutes(app);
        mapCelestialRoutes(app);
    }

    private static void mapTestRoutes(Javalin app) {
        if ("test".equals(AppConfig.ENVIRONMENT)) {
            app.post("/reset", ctx -> Utility.resetTestDatabase());
        }
    }

    private static void mapAuthFilters(Javalin app) {
        app.before("/planetarium/*", userController::authenticateUser);
        app.before("/planetarium", userController::authenticateUser);
    }

    private static void mapExceptionHandlers(Javalin app) {
        app.exception(AuthenticationFailed.class, (exception, ctx) -> {
            viewController.login(ctx);
            ctx.status(401);
        });

        app.exception(UserFail.class, (exception, ctx) -> {
            ctx.status(400);
            ctx.result(exception.getMessage());
        });

        app.exception(PlanetFail.class, (exception, ctx) -> {
            ctx.status(400);
            ctx.result(exception.getMessage());
        });

        app.exception(MoonFail.class, (exception, ctx) -> {
            ctx.status(400);
            ctx.result(exception.getMessage());
        });
    }

    private static void mapViewRoutes(Javalin app) {
        app.get("/background", viewController::backgroundImage);
        app.get("/", viewController::login);
        app.get("/register", viewController::register);
        app.get("/planetarium", viewController::home);
    }

    private static void mapUserRoutes(Javalin app) {
        app.post("/login", userController::login);
        app.post("/register", userController::createUser);
        app.post("/logout", userController::logout);
    }

    private static void mapPlanetRoutes(Javalin app) {
        app.get("/planetarium/user/{ownerId}/planet", planetController::findAllByOwner);
        app.post("/planetarium/user/{ownerId}/planet", planetController::createPlanet);
        app.delete("/planetarium/user/{ownerId}/planet/{identifier}", planetController::deletePlanet);
    }

    private static void mapMoonRoutes(Javalin app) {
        app.post("/planetarium/planet/{planetId}/moon", moonController::createMoon);
        app.delete("/planetarium/moon/{identifier}", moonController::deleteMoon);
    }

    private static void mapCelestialRoutes(Javalin app) {
        app.get("/planetarium/user/{userId}/moon", celestialController::findUserMoons);
    }
}
package com.revature;

import com.revature.exceptions.ConfigurationFail;
import com.revature.utility.AppConfig;
import com.revature.utility.PlanetariumSetup;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    public static void main(String[] args) {
        try {
            AppConfig.configureAppProperties(args);

            Javalin app = Javalin.create((config) -> {
                config.bundledPlugins.enableCors((cors) -> cors.addRule((it) -> it.anyHost()));
                config.bundledPlugins.enableDevLogging();

                config.staticFiles.add(staticFiles -> {
                    staticFiles.hostedPath = "/";
                    staticFiles.directory = "/";
                    staticFiles.location = Location.CLASSPATH;
                });
            });

            PlanetariumSetup.mapRoutes(app);

            app.start(8080);
        } catch (ConfigurationFail exception) {
            exception.printStackTrace();
        }
    }
}

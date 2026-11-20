package com.revature.utility;

import com.revature.exceptions.ConfigurationFail;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    public static String DATABASE_URL = System.getenv("DATABASE_URL");
    public static String PLANETARIUM_URL = System.getenv("PLANETARIUM_URL");
    public static String ENVIRONMENT = System.getenv("ENVIRONMENT");

    public static final Map<String, String> configProperties = new HashMap<>();

    public static void configureAppProperties(String[] args) throws ConfigurationFail {
        if (args.length > 0) {
            if (args.length % 2 != 0) {
                throw new ConfigurationFail("Invalid number of command line arguments");
            }

            for (int i = 0; i < args.length; ++i) {
                String arg = args[i];

                if (!configProperties.containsKey(arg)) {
                    throw new ConfigurationFail("Invalid argument: " + arg);
                }

                switch ((String) configProperties.get(arg)) {
                    case "DATABASE_URL":
                        ++i;
                        DATABASE_URL = args[i];
                        break;
                    case "PLANETARIUM_URL":
                        ++i;
                        PLANETARIUM_URL = args[i];
                        break;
                    case "ENVIRONMENT":
                        ++i;
                        ENVIRONMENT = args[i];
                        break;
                }
            }
        }
    }

    static {
        configProperties.put("--database-url", "DATABASE_URL");
        configProperties.put("--planetarium-url", "PLANETARIUM_URL");
        configProperties.put("--environment", "ENVIRONMENT");
    }
}

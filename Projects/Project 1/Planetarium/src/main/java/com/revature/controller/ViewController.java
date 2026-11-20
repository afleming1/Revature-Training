package com.revature.controller;

import com.revature.utility.AppConfig;
import io.javalin.http.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ViewController {
    public String loadPage(String page) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(String.format("pages/%s", page));
        StringBuilder contentBuilder = new StringBuilder();

        System.out.println(inputStream.toString());

        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            char[] buffer = new char[4096];

            int read;
            while ((read = reader.read(buffer)) != -1) {
                contentBuilder.append(buffer, 0, read);
            }
        }

        String content = contentBuilder.toString();

        return content.replaceAll("\\{URL\\}:\\{PORT\\}", AppConfig.PLANETARIUM_URL);
    }

    public void login(Context ctx) {
        try {
            String content = this.loadPage("login.html");

            ctx.html(content);
            ctx.status(200);
        } catch (IOException exception) {
            exception.printStackTrace();
            ctx.status(400);
        }
    }

    public void home(Context ctx) {
        try {
            String content = this.loadPage("home.html");

            ctx.html(content);
            ctx.status(200);
        } catch (IOException exception) {
            exception.printStackTrace();
            ctx.status(400);
        }
    }

    public void register(Context ctx) {
        try {
            String content = this.loadPage("create.html");

            ctx.html(content);
            ctx.status(200);
        } catch (IOException exception) {
            exception.printStackTrace();
            ctx.status(400);
        }
    }

    public void backgroundImage(Context ctx) throws IOException {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("images/galaxy-4.jpg")) {
            if (inputStream != null) {
                byte[] fileBytes = inputStream.readAllBytes();

                String imageDataBase64 = Base64.getEncoder().encodeToString(fileBytes);

                ctx.result(imageDataBase64);
            } else {
                ctx.result("Resource not found");
                ctx.status(404);
            }
        }
    }
}

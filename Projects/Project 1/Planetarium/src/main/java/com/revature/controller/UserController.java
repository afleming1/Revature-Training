package com.revature.controller;

import com.revature.entities.User;
import com.revature.exceptions.AuthenticationFailed;
import com.revature.service.user.UserService;
import io.javalin.http.Context;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void createUser(Context ctx) {
        User user = ctx.bodyAsClass(User.class);

        String result = this.userService.createUser(user);

        ctx.status(201).json(result);
    }

    public void login(Context ctx) {
        User credentials = ctx.bodyAsClass(User.class);
        User user = this.userService.authenticate(credentials);

        ctx.sessionAttribute("user", user.getUsername());
        ctx.status(200).json(user);
    }

    public void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.status(401).json("Logged out");
    }

    public void authenticateUser(Context ctx) {
        if (ctx.req().getSession(false) == null) {
            throw new AuthenticationFailed("Please log in first");
        }
    }
}

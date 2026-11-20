package com.revature.service.user;

import com.revature.entities.User;

public interface UserService {
    String createUser(User newUser);

    User authenticate(User credentials);
}

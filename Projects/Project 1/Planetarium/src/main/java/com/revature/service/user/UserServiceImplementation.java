package com.revature.service.user;

import com.revature.entities.User;
import com.revature.exceptions.UserFail;
import com.revature.repository.user.UserDao;

import java.sql.SQLException;

public class UserServiceImplementation implements UserService {
    private UserDao userDao;

    public UserServiceImplementation(UserDao userDao) {
        this.userDao = userDao;
    }

    public String createUser(User newUser) {
        try {
            String username = newUser.getUsername();

            if (username == null) {
                throw new UserFail("Invalid username");
            } else if (this.userDao.findUserByUsername(username) != null) {
                throw new UserFail("Invalid username");
            } else if (5 <= username.length() && username.length() <= 30) {
                if (!username.matches("^[a-zA-Z][a-zA-Z0-9-_]*$")) {
                    throw new UserFail("Invalid username");
                } else {
                    String password = newUser.getPassword();

                    if (password == null) {
                        return "Invalid password";
                    } else if (5 <= password.length() && password.length() <= 30) {
                        if (!password.matches("^[a-zA-Z0-9-_]*$")) {
                            throw new UserFail("Invalid password");
                        } else if (password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*")) {
                            User createdUser = this.userDao.createUser(newUser);

                            if (createdUser != null) {
                                return "User created successfully";
                            } else {
                                throw new UserFail("Failed to create user, please try again");
                            }
                        } else {
                            throw new UserFail("Invalid password");
                        }
                    } else {
                        throw new UserFail("Invalid password");
                    }
                }
            } else {
                throw new UserFail("Invalid username");
            }
        } catch (SQLException exception) {
            if (!exception.getMessage().contains("username_length_check") && !exception.getMessage().contains("username_character_check")) {
                if (!exception.getMessage().contains("password_length_check") && !exception.getMessage().contains("password_character_check")) {
                    throw new UserFail("Failed to create user, please try again");
                } else {
                    throw new UserFail("Invalid password");
                }
            } else {
                throw new UserFail("Invalid username");
            }
        }
    }

    public User authenticate(User credentials) {
        try {
            User foundUser = this.userDao.findUserByUsername(credentials.getUsername());

            if (foundUser != null && foundUser.getPassword().equals(credentials.getPassword())) {
                foundUser.setPassword(null);

                return foundUser;
            } else {
                throw new UserFail("Invalid credentials");
            }
        } catch (SQLException exception) {
            throw new UserFail("Invalid credentials");
        }
    }
}

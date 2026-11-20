package com.revature.repository.user;

import com.revature.entities.User;
import com.revature.utility.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImplementation implements UserDao {
    public User createUser(User newUser) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)", 1)) {
                statement.setString(1, newUser.getUsername());
                statement.setString(2, newUser.getPassword());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        newUser.setId(resultSet.getInt(1));

                        return newUser;
                    }
                }
            }
        }

        return null;
    }

    public User findUserByUsername(String username) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
                statement.setString(1, username);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt("id"));
                        user.setUsername(resultSet.getString("username"));
                        user.setPassword(resultSet.getString("password"));

                        return user;
                    }
                }
            }
        }

        return null;
    }
}

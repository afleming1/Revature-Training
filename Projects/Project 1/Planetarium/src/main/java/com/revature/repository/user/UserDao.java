package com.revature.repository.user;

import com.revature.entities.User;

import java.sql.SQLException;

public interface UserDao {
    User createUser(User user) throws SQLException;

    User findUserByUsername(String username) throws SQLException;
}

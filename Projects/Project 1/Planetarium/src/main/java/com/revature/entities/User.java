package com.revature.entities;

public class User {
    private int id;

    private String username;
    private String password;

    public User() {}

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return String.format("User ID: %d, Username: %s, Password: %s", this.id, this.username, this.password);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = 31 * result + this.id;
        result = 31 * result + (this.username == null ? 0 : this.username.hashCode());
        result = 31 * result + (this.password == null ? 0 : this.password.hashCode());

        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            User other = (User)obj;

            if (this.id != other.id) {
                return false;
            } else {
                if (this.username == null) {
                    if (other.username != null) {
                        return false;
                    }
                } else if (!this.username.equals(other.username)) {
                    return false;
                }

                if (this.password == null) {
                    if (other.password != null) {
                        return false;
                    }
                } else if (!this.password.equals(other.password)) {
                    return false;
                }

                return true;
            }
        }
    }
}

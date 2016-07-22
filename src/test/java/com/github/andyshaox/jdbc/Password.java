package com.github.andyshaox.jdbc;

public class Password {
    private String id;
    private String password;
    private String userId;

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

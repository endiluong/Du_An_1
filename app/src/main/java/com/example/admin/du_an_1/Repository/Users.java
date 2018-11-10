package com.example.admin.du_an_1.Repository;

public class Users {
    private String id;
    private String userName;
    private String password;

    public Users() {
    }

    public Users(String userName, String password) {
        this.id=null;
        this.userName = userName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

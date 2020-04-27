package com.example.exa_appsii_prac_2;

public class User {
    private int id;
    private String name;
    private String lastN;
    private String username;
    private String pass;

    public User() {
        id = 1;
        name = "Diego";
        lastN = "Rodríguez";
        username = "diegrod";
        pass = "1234";
    }

    public User(int id, String name, String lastN ,String username, String pass) {
        this.id = id;
        this.name = name;
        this.lastN = lastN;
        this.username = username;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

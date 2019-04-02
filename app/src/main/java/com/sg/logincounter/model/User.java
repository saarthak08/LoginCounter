package com.sg.logincounter.model;


public class User {
    private String name;
    private String password;
    private Integer id;
    private Integer counter;

    public User(String name, String password, Integer id, Integer counter) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}

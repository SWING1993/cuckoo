package com.cuckoo.domain;

import java.security.PrivateKey;

public class User {

    private Integer id;
    private String phone;
    private String name;
    private String password;

    public  User() {

    }

    public User(String phone, String name, String password) {
        this.phone = phone;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

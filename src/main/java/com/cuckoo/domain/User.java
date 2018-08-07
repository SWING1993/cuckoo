package com.cuckoo.domain;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

    // 用户id
    private long id;
    // 用户手机号码
    private String phone;
    // 用户名
    private String username;
    // 用户密码
    private String password;
    // 用户状态
    private byte state;
    // 创建时间
    private Date created;
    // 修改时间
    private Date updated;
    //用户所有角色值，用于shiro做角色权限的判断
    private Set<String> roles = new HashSet<>();
    //用户所有权限值，用于shiro做资源权限的判断
    private Set<String> perms = new HashSet<>();

    public User() {

    }

    public User(String phone, String username, String password) {
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPerms() {
        return perms;
    }

    public void setPerms(Set<String> perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", created=" + created +
                ", updated=" + updated +
                ", roles=" + roles +
                ", perms=" + perms +
                '}';
    }
}

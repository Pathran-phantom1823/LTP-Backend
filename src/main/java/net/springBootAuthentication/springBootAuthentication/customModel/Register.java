package net.springBootAuthentication.springBootAuthentication.customModel;

import java.sql.Date;


public class Register {

    private long id;

    
    private String username;

    private String email;

    private String password;

    private Integer roleId;

    private String expired;

    private String isMember;

    private String isDisabled;

    private Date createdAt;

    private String name;
    
    public Register() {
    }

    public Register(long id, String username, String email, String password, Integer roleId, String expired,
            String isMember, String isDisabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.expired = expired;
        this.isMember = isMember;
        this.isDisabled = isDisabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getisDisabled() {
        return isDisabled;
    }

    public void setisDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
    
}

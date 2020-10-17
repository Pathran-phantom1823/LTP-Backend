package net.springBootAuthentication.springBootAuthentication.model;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Email")
    private String email;

    @Column(name= "Password")
    private String password;

    @Column(name="roleId")
    private Integer roleId;

    @Column(name="expired")
    private String expired;

    @Column(name = "isMember")
    private String isMember;

    @Column(name = "isDisabled")
    private String isDisabled;

    @Column(name = "createdAt")
    private LocalDate createdAt;
    
    public Registration() {
    }

    public Registration(long id, String username, String email, String password, Integer roleId, String expired,
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    
   
    
}

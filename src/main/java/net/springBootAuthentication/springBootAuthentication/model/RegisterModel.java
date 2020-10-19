package net.springBootAuthentication.springBootAuthentication.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblaccount")

public class RegisterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roleid")
    private long roleid;

    @Column(name = "isDisabled")
    private String isDisabled;

    @Column(name="expired")
    private String expired;

    @Column(name = "dateCreated")
    private LocalDate dateCreated;

    @Column(name = "isMember")
    private String isMember;

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

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

   

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String isDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
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

    public RegisterModel() {
        
    }

    public RegisterModel(long id, String username, String email, String password, long roleid, String isDisabled,
            String expired, LocalDate dateCreated, String isMember) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleid = roleid;
        this.isDisabled = isDisabled;
        this.expired = expired;
        this.dateCreated = dateCreated;
        this.isMember = isMember;
    }

    

}
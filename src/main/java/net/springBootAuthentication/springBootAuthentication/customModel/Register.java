package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public class Register {
    private long id;

    private String username;

    private String password;
    
    private String email;

    private boolean isDisabled;

    private LocalDate dateCreated;

    private long roleId;

    private String roleType;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Register(long id, String username, String password, String email, boolean isDisabled, LocalDate dateCreated,
            long roleId, String roleType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isDisabled = isDisabled;
        this.dateCreated = dateCreated;
        this.roleId = roleId;
        this.roleType = roleType;
    }

}

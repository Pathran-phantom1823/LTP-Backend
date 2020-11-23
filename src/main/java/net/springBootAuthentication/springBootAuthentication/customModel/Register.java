package net.springBootAuthentication.springBootAuthentication.customModel;


public class Register {
    private long id;

    private String username;

    private String password;
    
    private String email;

    private String isDisabled;

    private String dateCreated;

    private String isMember;

    private long roleId;

    private String roleType;

    private String expired;


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

    public String isDisabled() {
        return isDisabled;
    }

    public void setDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
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

    public Register(long id, String username, String password, String email, String isDisabled, String dateCreated,
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

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getIsDisabled() {
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

}

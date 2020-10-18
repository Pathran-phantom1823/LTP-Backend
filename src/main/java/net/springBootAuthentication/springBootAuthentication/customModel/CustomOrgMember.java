package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public class CustomOrgMember {
    private long id;

    private String username;

    private String password;
    
    private String email;

    private String isDisabled;

    private LocalDate dateCreated;

    private String isMember;

    private long roleId;

    private String roleType;

    private String expired;

    private Long orgId;

    public CustomOrgMember() {
    }

    public CustomOrgMember(long id, String username, String password, String email, String isDisabled,
            LocalDate dateCreated, String isMember, long roleId, String roleType, String expired, Long orgId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isDisabled = isDisabled;
        this.dateCreated = dateCreated;
        this.isMember = isMember;
        this.roleId = roleId;
        this.roleType = roleType;
        this.expired = expired;
        this.orgId = orgId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
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

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}

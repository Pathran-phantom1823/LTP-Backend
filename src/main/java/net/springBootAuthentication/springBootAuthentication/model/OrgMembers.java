package net.springBootAuthentication.springBootAuthentication.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "getOrganizationMembers",
        procedureName = "getMembers",
        parameters = {
            @StoredProcedureParameter(
                mode = ParameterMode.IN,
                name = "id",
                type = Integer.class
            )
        }
    )
})
@Table(name = "orgMembersAccount")
public class OrgMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long orgId;

    private Long accountId;

    private String createAt;

    public OrgMembers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

	public OrgMembers(Long id, Long orgId, Long accountId, String createAt) {
		this.id = id;
		this.orgId = orgId;
		this.accountId = accountId;
		this.createAt = createAt;
	}
}

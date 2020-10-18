package net.springBootAuthentication.springBootAuthentication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity

@Table(name = "JobsTrasactions")
public class JobsTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private Long postById;

    public JobsTransaction() {
    }

    public JobsTransaction(Long id, Long jobId, Long postById) {
        this.id = id;
        this.jobId = jobId;
        this.postById = postById;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getPostById() {
        return postById;
    }

    public void setPostById(Long postById) {
        this.postById = postById;
    }

}

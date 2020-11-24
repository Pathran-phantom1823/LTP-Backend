package net.springBootAuthentication.springBootAuthentication.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JobTransactionModel
 */
@Entity
@Table(name = "tblJobTransaction")
public class JobTransactionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postedBy")
    private Long postedBy;

    @Column(name = "workedBy")
    private Long workedBy;

    @Column(name = "jobId")
    private Long jobId;

    @Column(name = "finishFile")
    private String finishFile;

    @Column(name = "dateFinished")
    private String dateFinished;

    @Column(name = "datePosted")
    private String datePosted;

    @Column(name = "orgId")
    private Long orgId;

    public JobTransactionModel() {
    }

    public JobTransactionModel(Long id, Long postedBy, Long workedBy, String finishFile,
    String dateFinished, String datePosted) {
        this.id = id;
        this.postedBy = postedBy;
        this.workedBy = workedBy;
        this.finishFile = finishFile;
        this.dateFinished = dateFinished;
        this.datePosted = datePosted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Long postedBy) {
        this.postedBy = postedBy;
    }

    public Long getWorkedBy() {
        return workedBy;
    }

    public void setWorkedBy(Long workedBy) {
        this.workedBy = workedBy;
    }

    public String getFinishFile() {
        return finishFile;
    }

    public void setFinishFile(String finishFile) {
        this.finishFile = finishFile;
    }

    public String getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    
}
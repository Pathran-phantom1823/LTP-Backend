package net.springBootAuthentication.springBootAuthentication.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JobApplicants")
public class JobApplicants {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long applicantId;

    private Long jobId;

    private String status;

    private LocalDate dateApplied;

    private LocalDate dateAccepted;

    private LocalDate dateFinished;

    private String finishedFile;

    public JobApplicants() {
    }

    public JobApplicants(Long id, Long applicantId, Long jobId, String status, LocalDate dateApplied) {
        this.id = id;
        this.applicantId = applicantId;
        this.jobId = jobId;
        this.status = status;
        this.dateApplied = dateApplied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public LocalDate getDateAccepted() {
        return dateAccepted;
    }

    public void setDateAccepted(LocalDate dateAccepted) {
        this.dateAccepted = dateAccepted;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(LocalDate dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getFinishedFile() {
        return finishedFile;
    }

    public void setFinishedFile(String finishedFile) {
        this.finishedFile = finishedFile;
    }
}

package net.springBootAuthentication.springBootAuthentication.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SaveJob")
public class SaveJob {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jobId")
    private Long jobId;

    @Column(name = "postedById")
    private Long postedById;

    @Column(name = "saveById")
    private Long savedById;

    @Column(name = "dateSaved")
    private String dateSaved;

    public SaveJob() {
    }

    public SaveJob(Long id, Long jobId, Long postedById, Long savedById, String dateSaved) {
        this.id = id;
        this.jobId = jobId;
        this.postedById = postedById;
        this.savedById = savedById;
        this.dateSaved = dateSaved;
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

    public Long getPostedById() {
        return postedById;
    }

    public void setPostedById(Long postedById) {
        this.postedById = postedById;
    }

    public Long getSavedById() {
        return savedById;
    }

    public void setSavedById(Long savedById) {
        this.savedById = savedById;
    }

    public String getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(String dateSaved) {
        this.dateSaved = dateSaved;
    }
}

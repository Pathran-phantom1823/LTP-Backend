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

    @Column(name = "finishFile")
    private String finishFile;

    @Column(name = "addressId")
    private Long addressId;

    @Column(name = "dateFinished")
    private LocalDate dateFinished;

    @Column(name = "datePosted")
    private LocalDate datePosted;

    public JobTransactionModel() {
    }

    public JobTransactionModel(Long id, Long postedBy, Long workedBy, String finishFile, Long addressId,
            LocalDate dateFinished, LocalDate datePosted) {
        this.id = id;
        this.postedBy = postedBy;
        this.workedBy = workedBy;
        this.finishFile = finishFile;
        this.addressId = addressId;
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(LocalDate dateFinished) {
        this.dateFinished = dateFinished;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }
    
}
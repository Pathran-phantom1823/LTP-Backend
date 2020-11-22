package net.springBootAuthentication.springBootAuthentication.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tblReports")
public class ReportsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "accountId")
    private Long accountId;

    @Column(name = "reportTimestamp")
    private String reportTimestamp;

    @Column( name = "topic")
    private String topic;

    @Column (name = "description")
    private String description;

    @Column (name = "resolve")
    private String resolve;

    @Column (name = "dateResolve")
    private String dateResolve;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getReportTimestamp() {
        return reportTimestamp;
    }

    public void setReportTimestamp(String reportTimestamp) {
        this.reportTimestamp = reportTimestamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResolve() {
        return resolve;
    }

    public void setResolve(String resolve) {
        this.resolve = resolve;
    }

    public String getDateResolve() {
        return dateResolve;
    }

    public void setDateResolve(String dateResolve) {
        this.dateResolve = dateResolve;
    }

    public ReportsModel(long id, Long accountId, String reportTimestamp, String topic, String description,
            String resolve, String dateResolve) {
        this.id = id;
        this.accountId = accountId;
        this.reportTimestamp = reportTimestamp;
        this.topic = topic;
        this.description = description;
        this.resolve = resolve;
        this.dateResolve = dateResolve;
    }

    public ReportsModel(){
        
    }
    
}
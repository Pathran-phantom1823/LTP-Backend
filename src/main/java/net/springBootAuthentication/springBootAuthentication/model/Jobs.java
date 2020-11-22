package net.springBootAuthentication.springBootAuthentication.model;

import java.util.Date;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "getFiles",
        procedureName = "getFile",
        parameters = {
            @StoredProcedureParameter(
                mode = ParameterMode.IN,
                name = "id",
                type = Integer.class
            )
        }
    )
})
@Table(name = "Jobs")
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
        private Long id;

        @Column(name = "postedById")
        private Long postById;

        
        @Column(name = "title")
        private String title;

        @Column(name = "description")
        private String description;

        @Column(name = "category")
        private String category;

        @Column(name = "languageFrom")
        private String  languageFrom;
        
        @Column(name = "languageTo")
        private String languageTo;

        @Column(name = "fromDate")
        private String fromDate;

        @Column(name = "toDate")
        private String toDate;

        @Column(name = "priceType")
        private String priceType;

        @Column(name = "fromPrice")
        private Integer fromPrice;

        @Column(name = "toPrice")
        private Integer toPrice;

        @Column(name = "fixedPrice")
        private Integer fixedPrice;

        @Column(name = "file")
        private String file;

        @Column(name = "visibility")
        private String visibility;

        @Column(name = "levelOfConfidentiality")
        private String levelOfConfidentiality;

        @Column(name = "type")
        private String type;

        @Column(name = "subject")
        private String subject;

        @Column(name = "datePosted")
        private String datePosted;

        @Column(name = "deleted")
        private String deleted;

        
        private String isAvailable;

    public Jobs(Long id, String title, String description, String category, String languageFrom,
            String languageTo, String fromDate, String toDate, String priceType,
            Integer fromPrice, Integer toPrice, String visibility, String levelOfConfidentiality, String file) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.priceType = priceType;
        this.fromPrice = fromPrice;
        this.toPrice = toPrice;
        this.file = file;
        this.visibility = visibility;
        this.levelOfConfidentiality = levelOfConfidentiality;
    }

    public Jobs() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public void setLanguageFrom(String  languageFrom) {
        this.languageFrom = languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public void setLanguageTo(String languageTo) {
        this.languageTo = languageTo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Integer getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Integer fromPrice) {
        this.fromPrice = fromPrice;
    }

    public Integer getToPrice() {
        return toPrice;
    }

    public void setToPrice(Integer toPrice) {
        this.toPrice = toPrice;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getlevelOfConfidentiality() {
        return levelOfConfidentiality;
    }

    public void setlevelOfConfidentiality(String levelOfConfidentiality) {
        this.levelOfConfidentiality = levelOfConfidentiality;
    }

    public Long getPostById() {
        return postById;
    }

    public void setPostById(Long postById) {
        this.postById = postById;
    }

    public Integer getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(Integer fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    
}

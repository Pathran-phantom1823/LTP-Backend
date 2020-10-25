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
@Table(name = "tblComments")
public class CommentsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "commentById")
    private Long commentById;

    @Column(name = "dateCommented")
    private LocalDate dateCommented;

    @Column(name = "status")
    private String status;


    public CommentsModel() {
    }

    public CommentsModel(Long id, String comment, Long commentById, LocalDate dateCommented) {
        this.id = id;
        this.comment = comment;
        this.commentById = commentById;
        this.dateCommented = dateCommented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCommentById() {
        return commentById;
    }

    public void setCommentById(Long commentById) {
        this.commentById = commentById;
    }

    public LocalDate getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented(LocalDate dateCommented) {
        this.dateCommented = dateCommented;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
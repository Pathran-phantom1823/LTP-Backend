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
@Table(name = "tblCommentLikes")
public class CommentLikesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commentId")
    private Long commentId;

    @Column(name = "likeById")
    private Long likeById;

    @Column(name = "date")
    private LocalDate date;

    public CommentLikesModel() {
    }

    public CommentLikesModel(Long id, Long commentId, Long likeById, LocalDate date) {
        this.id = id;
        this.commentId = commentId;
        this.likeById = likeById;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getLikeById() {
        return likeById;
    }

    public void setLikeById(Long likeById) {
        this.likeById = likeById;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDateCommented(LocalDate date) {
        this.date = date;
    }
    
}
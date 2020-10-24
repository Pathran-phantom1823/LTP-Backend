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
@Table(name = "tblForumTransactions")
public class ForumTransactionsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountId")
    private Long accountId;

    @Column(name = "postId")
    private Long postId;

    @Column(name = "commentId")
    private Long commentId;

    @Column(name = "Date")
    private LocalDate Date;


    public ForumTransactionsModel() {
    }

    public ForumTransactionsModel(Long id, Long accountId, Long postId, Long commentId, LocalDate Date) {
        this.id = id;
        this.accountId = accountId;
        this.postId = postId;
        this.commentId = commentId;
        this.Date = Date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
}
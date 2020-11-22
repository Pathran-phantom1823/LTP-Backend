package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public class CustomComment {
    private String description;
    private String topic;
    private String username;
    private String datePosted;
    private Long accountId ;
    private Long transactionId;
    private Long postId;
    private Long commentId;
    private Long Id;
    private String date;
    private String comment;
    private Long commentedById;
    private String dateCommented;

    public CustomComment() {
    }

    public CustomComment(String description, String topic, String username, String datePosted, Long accountId,
            Long transactionId, Long postId, Long commentId, Long id, String date) {
        this.description = description;
        this.topic = topic;
        this.username = username;
        this.datePosted = datePosted;
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.postId = postId;
        this.commentId = commentId;
        Id = id;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCommentedById() {
        return commentedById;
    }

    public void setCommentedById(Long commentedById) {
        this.commentedById = commentedById;
    }

    public String getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented(String dateCommented) {
        this.dateCommented = dateCommented;
    }


}

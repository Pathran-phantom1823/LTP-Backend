package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;


public interface CustomForum {
    String getDescription();
    String getTopic();
    String getUsername();
    String getComment();
    Long getNumberOfComments();
    LocalDate getDatePosted();
    Long getAccountId() ;
    Long getLikes();
    LocalDate getDateCommented();
    Long getTransactionId();
    Long getPostId();
    Long getCommentId();
    Long getId();
    LocalDate getDate();
    String getStatus();
    Long getLikeById();
}

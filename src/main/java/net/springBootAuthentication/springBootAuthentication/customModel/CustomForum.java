package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;


public interface CustomForum {
    String getDescription();
    String getTopic();
    String getUsername();
    String getFirstName();
    String getLastName();
    String getComment();
    Long getNumberOfComments();
    String getDatePosted();
    Long getAccountId() ;
    Long getLikes();
    String getDateCommented();
    Long getTransactionId();
    Long getPostId();
    Long getCommentId();
    Long getId();
    String getDate();
    String getStatus();
    Long getLikeById();
}

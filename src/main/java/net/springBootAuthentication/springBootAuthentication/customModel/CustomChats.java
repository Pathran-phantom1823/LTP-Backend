package net.springBootAuthentication.springBootAuthentication.customModel;

import java.sql.Date;

public interface CustomChats {
    
    String getMessage();
    Long getSenderId();
    Long getMemberId();
    String getRoomName();
    Long getOwnerId();
    String getDate();
    String getFromTime();
    String getToTime();
    String getUsername();
}

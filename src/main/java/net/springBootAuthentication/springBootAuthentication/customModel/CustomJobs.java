package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

public  interface CustomJobs {
    Long getId();

     String getTitle();

     String getDescription();
    
     String getcategory();
    
     String getLanguageFrom();
    
     String getLanguageTo();
    
     String getFromDate();
    
     String getToDate();
    
     Integer getFromPrice();

     Integer getToPrice();

     Integer getFixedPrice();

     Integer getPostedById();

     String getType();
        
     String getSubject();

     String getUsername();

     String getEmail();

     LocalDate getdatePosted();

     Integer getBids();

     String getFile();
}

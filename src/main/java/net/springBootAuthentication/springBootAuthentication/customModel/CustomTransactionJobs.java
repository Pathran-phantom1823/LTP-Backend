package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public interface CustomTransactionJobs {
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

     String getPostedByUsername();

     String getEmail();

     LocalDate getdatePosted();

     Integer getBids();

     String getFile();

     String getWorkedByUsername();

     String getOrgName();

     String getFinishFiles();

     LocalDate getDateFinishDate();

}

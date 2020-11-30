package net.springBootAuthentication.springBootAuthentication.customModel;

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

     String getdatePosted();

     String getLevelOfConfidentiality();

     String getVisibility();

     String getPriceType();

     Integer getBids();

     String getFile();

     String getAssignedTo();

     String getFirstName();

     String getLastname();

     String getAssignedToFirstName();

     String getAssignedToLastname();

     String getCity();

     String getCountry();

     String getAssigned();

}

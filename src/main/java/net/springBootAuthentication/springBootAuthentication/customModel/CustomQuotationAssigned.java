package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public interface CustomQuotationAssigned {
    
    Long getJobId();

    LocalDate getDatePosted();

    LocalDate getDateAssigned();
    
    String getTitle();

    String getPostedBy();

    String getAssignedTo();

    String getAssignedBy();

    String getPostedByFirstName();

    String getAssignedToFirstName();

    String getAssignedByFirstName();

    String getPostedByLastName();

    String getAssignedToLastName();

    String getAssignedByLastName();

}

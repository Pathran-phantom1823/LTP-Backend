package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public interface CustomJobApplicant {
    
    String getTitle();
    Float getFixedPrice();
    Float getFromPrice();
    Float getToPrice();
    String getPostedBy();
    String getFirstName();
    String getLastName();
    LocalDate getDateAccepted();
    LocalDate getDateFinished();
    LocalDate getDateApplied();
}

package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public interface CustomJobApplicant {
    
    String getTitle();
    Float getFixedPrice();
    Float getFromPrice();
    Float getToPrice();
    String getPostedBy();
    LocalDate getDateAccepted();
    LocalDate getDateFinished();
    LocalDate getDateApplied();
}

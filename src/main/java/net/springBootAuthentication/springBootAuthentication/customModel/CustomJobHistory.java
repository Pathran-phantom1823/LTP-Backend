package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public interface CustomJobHistory {
    LocalDate getDateFinish();

    LocalDate getDatePosted();
    
    String getWorkedByUsername();

    String getFirstName();

    String getLastName();

    Integer getFromPrice();

    Integer getToPrice();

    Integer getFixedPrice();

    String getTitle();

}

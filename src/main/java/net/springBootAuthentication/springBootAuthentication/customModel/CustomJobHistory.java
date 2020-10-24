package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public interface CustomJobHistory {
    LocalDate getDateFinish();

    LocalDate getDatePosted();
    
    String getWorkedByUsername();

    Integer getFromPrice();

    Integer getToPrice();

    Integer getFixedPrice();

    String getTitle();

}

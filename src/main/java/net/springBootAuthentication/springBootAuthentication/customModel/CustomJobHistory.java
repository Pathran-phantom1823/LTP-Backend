package net.springBootAuthentication.springBootAuthentication.customModel;

public interface CustomJobHistory {
    String getJobId();

    String getDateFinish();

    String getDatePosted();
    
    String getWorkedByUsername();

    String getFirstName();

    String getLastName();

    Integer getFromPrice();

    Integer getToPrice();

    Integer getFixedPrice();

    String getTitle();

    String getFinishedFile();

}

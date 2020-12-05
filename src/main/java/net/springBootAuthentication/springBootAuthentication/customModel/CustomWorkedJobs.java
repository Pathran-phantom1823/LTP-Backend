package net.springBootAuthentication.springBootAuthentication.customModel;

public interface CustomWorkedJobs {
    Long getJobId();
    Long getApplicantId();
    String getJobTitle();
    String getPostedByUsername();
    String getPostedByEmail();
    String getWorkedByUsername();
    String getWorkedByEmail();
    String getDateFinished();
    String getDatePosted();
    String getDateAccepted();
    String getIsPaid();
}

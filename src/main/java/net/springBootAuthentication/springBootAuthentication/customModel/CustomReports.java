package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;

public interface CustomReports {
    String getDescription();
    String getTopic();
    String getResolve();
    String getUsername();
    String getEmail();
    Long  getAccountId();
    Long getReportId();
    LocalDate getDateResolve();
    LocalDate getReportTimestamp();

}

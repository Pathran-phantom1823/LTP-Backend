package net.springBootAuthentication.springBootAuthentication.customModel;

public interface GetPaymentDetailsModel {
	public Long getId();
	public Long getPayer();
	public Long getJobId();
	public double getAmount();
	public String getTitle();
	public String getBillingEmail();
}

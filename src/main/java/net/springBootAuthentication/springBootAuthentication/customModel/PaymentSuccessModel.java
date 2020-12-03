package net.springBootAuthentication.springBootAuthentication.customModel;

public class PaymentSuccessModel {
	private String userID;
	private String paymentID;
	private String payerID;
	private String planID;
	private String dateCreated;
	
	public PaymentSuccessModel(String userID, String paymentID, String payerID, String planID, String dateCreated) {
		super();
		this.userID = userID;
		this.paymentID = paymentID;
		this.payerID = payerID;
		this.planID = planID;
		this.dateCreated = dateCreated;
	}
	
	public PaymentSuccessModel() {
		super();
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	public String getPayerID() {
		return payerID;
	}
	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}
	public String getPlanID() {
		return planID;
	}
	public void setPlanID(String planID) {
		this.planID = planID;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}

package net.springBootAuthentication.springBootAuthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tblPaypalPayment")
public class PaypalPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "subscriptionPlan")
	private String subscriptionPlan;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "payerAccountID")
	private int payerAccountID;
	
	@Lob
	@Column(name = "paymentDetails", columnDefinition = "LONGTEXT")
	private String paymentDetails;
	
	@Column(name = "planID")
	private int planID;
	
	@Column(name = "dateCreated")
	private String dateCreated;

	public PaypalPayment(long id, String subscriptionPlan, double amount, String currency, int payerAccountID,
			String paymentDetails, int planID, String dateCreated) {
		super();
		this.id = id;
		this.subscriptionPlan = subscriptionPlan;
		this.amount = amount;
		this.currency = currency;
		this.payerAccountID = payerAccountID;
		this.paymentDetails = paymentDetails;
		this.planID = planID;
		this.dateCreated = dateCreated;
	}

	public PaypalPayment() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(String subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getPayerAccountID() {
		return payerAccountID;
	}

	public void setPayerAccountID(int payerAccountID) {
		this.payerAccountID = payerAccountID;
	}

	public String getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public int getPlanID() {
		return planID;
	}

	public void setPlanID(int planID) {
		this.planID = planID;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}

package net.springBootAuthentication.springBootAuthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblServicePayments")
public class ServicePayment {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "jobId")
	private long jobId;
	
	@Column(name = "payerId")
	private long payerId;
	
	@Column(name = "isTransferred")
	private boolean isTransferred;
	
	@Column(name = "dateCreated")
	private String dateCreated;

	public ServicePayment() {
		super();
	}

	public ServicePayment(long id, double price, long jobId, long payerId, boolean isTransferred, String dateCreated) {
		super();
		this.id = id;
		this.price = price;
		this.jobId = jobId;
		this.payerId = payerId;
		this.isTransferred = isTransferred;
		this.dateCreated = dateCreated;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public long getPayerId() {
		return payerId;
	}

	public void setPayerId(long payerId) {
		this.payerId = payerId;
	}

	public boolean isTransferred() {
		return isTransferred;
	}

	public void setTransferred(boolean isTransferred) {
		this.isTransferred = isTransferred;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}

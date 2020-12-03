package net.springBootAuthentication.springBootAuthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblMembershipPlans")
public class MembershipPlanModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "plan")
	private String plan;
	
	@Column(name = "price")
	private double price;
		
	@Column(name = "description")
	private String description;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "dateCreated")
	private String dateCreated;

	public MembershipPlanModel(long id, String plan, double price, String description, String currency,
			String dateCreated) {
		super();
		this.id = id;
		this.plan = plan;
		this.price = price;
		this.description = description;
		this.currency = currency;
		this.dateCreated = dateCreated;
	}

	public MembershipPlanModel() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}

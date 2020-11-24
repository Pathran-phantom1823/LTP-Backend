package net.springBootAuthentication.springBootAuthentication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblprofiles")
public class ProfileModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "accountId")
	private long accountId;

	@Column(name = "addressId")
	private long addressId;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "age")
	private Integer age;

	@Column(name = "gender")
	private String gender;

	@Column(name = "birthdate")
	private Date birthdate;


	@Column(name = "pricing")
	private float pricing;

	@Column(name = "phonenumber")
	private String phonenumber;

	@Column(name = "image")
	private String image;

	@Column(name = "date_from")
	private String dateFrom;

	@Column(name = "date_to")
	private String dateTo;

	@Column(name = "time_from")
	private String timeFrom;

	@Column(name = "time_to")
	private String timeTo;

	public ProfileModel() {
	}

	public ProfileModel(long id, long accountId, long addressId, String firstname, String lastname, Integer age,
			String gender, Date birthdate, float pricing, String phonenumber, String image,
			String dateFrom, String dateTo, String timeFrom, String timeTo) {
		this.id = id;
		this.accountId = accountId;
		this.addressId = addressId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.gender = gender;
		this.birthdate = birthdate;
		this.pricing = pricing;
		this.phonenumber = phonenumber;
		this.image = image;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public float getPricing() {
		return pricing;
	}

	public void setPricing(float pricing) {
		this.pricing = pricing;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

}

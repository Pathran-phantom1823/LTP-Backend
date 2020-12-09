package net.springBootAuthentication.springBootAuthentication.customModel;

import java.time.LocalDate;
import java.util.Date;

public class CustomProfile {
    private Long id;

    private Long accountId;

    private Long skillId;

    private Long addressId;

    private Long categoryId;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private Integer age;

    private String gender;

    private String birthdate;

    private Float pricing;

    private String phonenumber;

    private String email;

    private String image;

    private String dateFrom;

    private String dateTo;

    private String timeFrom;

    private String timeTo;

    private String street;

    private String city;

    private String country;

    private Integer postalcode;

    private Integer zipcode;

    private LocalDate timeStamp;

    private LocalDate timestamps;

    private String skillname;

    private String category;

    private String billingEmail;

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public CustomProfile() {
    }

    public CustomProfile(Long id, Long accountId, Long skillId, Long addressId, Long categoryId,
            String username, String password, String firstname, String lastname, Integer age, String gender,
            String birthdate, Float pricing, String phonenumber, String email, String image, String dateFrom,
            String dateTo, String timeFrom, String timeTo, String street, String city, String country,
            Integer postalcode, Integer zipcode, LocalDate timeStamp,
            LocalDate timestamps, String skillname, String category) {
        this.id = id;
        this.accountId = accountId;
        this.skillId = skillId;
        this.addressId = addressId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.birthdate = birthdate;
        this.pricing = pricing;
        this.phonenumber = phonenumber;
        this.email = email;
        this.image = image;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalcode = postalcode;
        this.zipcode = zipcode;
        this.timeStamp = timeStamp;
        this.timestamps = timestamps;
        this.skillname = skillname;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Float getPricing() {
        return pricing;
    }

    public void setPricing(Float pricing) {
        this.pricing = pricing;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(Integer postalcode) {
        this.postalcode = postalcode;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocalDate getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(LocalDate timestamps) {
        this.timestamps = timestamps;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

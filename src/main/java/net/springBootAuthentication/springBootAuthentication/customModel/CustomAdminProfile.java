package net.springBootAuthentication.springBootAuthentication.customModel;

import java.util.Date;

public class CustomAdminProfile {
    private long id;

    private long accountId;

    private String firstname;

    private String lastname;

    private Integer age;

    private String birthdate;

    private String email;

    private String gender;

    private String phonenumber;

    private Long address_id;

    private Integer postalcode;

    private Integer zipcode;

    private String city;

    private String country;

    private String street;
    
    private String img;

    private String username;

    private String password;

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CustomAdminProfile() {
    }

    public CustomAdminProfile(long id, long accountId, String firstname, String lastname,
            Integer age, String birthdate, String email, String gender, String phonenumber, Long address_id,
            Integer postalcode, Integer zipcode, String city, String country,  String street,
            String img) {
        this.id = id;
        this.accountId = accountId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.birthdate = birthdate;
        this.email = email;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address_id = address_id;
        this.postalcode = postalcode;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.street = street;
        this.img = img;
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
}

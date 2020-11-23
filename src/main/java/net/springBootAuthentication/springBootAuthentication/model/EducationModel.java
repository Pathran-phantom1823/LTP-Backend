package net.springBootAuthentication.springBootAuthentication.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbleducations")
public class EducationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "profileId")
    private long profileId;

    @Column(name = "schoolname")
    private String schoolname;

    @Column(name = "schoolyear")
    private Integer schoolyear;

    @Column(name = "addressId")
    private long addressId;

    @Column(name = "timestamps")
    private LocalDate timestamps;

    public EducationModel() {
    }

    public EducationModel(long id, long profileId, String schoolname, Integer schoolyear, long addressId,
    LocalDate timestamps) {
        this.id = id;
        this.profileId = profileId;
        this.schoolname = schoolname;
        this.schoolyear = schoolyear;
        this.addressId = addressId;
        this.timestamps = timestamps;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public Integer getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(Integer schoolyear) {
        this.schoolyear = schoolyear;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public LocalDate getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(LocalDate timestamps) {
        this.timestamps = timestamps;
    }

}

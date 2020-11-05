package net.springBootAuthentication.springBootAuthentication.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblprofileskills")
public class ProfileSkillsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "skillid")
    private long skillid;

    @Column(name = "profileid")
    private long profileid;

    @Column(name = "timestamps")
    private LocalDate timestamps;

    public ProfileSkillsModel() {
    }

    public ProfileSkillsModel(long id, long skillid, long profileid, LocalDate timestamps) {
        this.id = id;
        this.skillid = skillid;
        this.profileid = profileid;
        this.timestamps = timestamps;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSkillid() {
        return skillid;
    }

    public void setSkillid(long skillid) {
        this.skillid = skillid;
    }

    public long getProfileid() {
        return profileid;
    }

    public void setProfileid(long profileid) {
        this.profileid = profileid;
    }

    public LocalDate getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(LocalDate timestamps) {
        this.timestamps = timestamps;
    }

}

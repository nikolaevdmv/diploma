package com.nikolaev.conference;


import com.nikolaev.conference_user_roles.ConferenceUserRoles;
import com.nikolaev.submission.Submission;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "conference")
public class Conference implements Serializable {

    @OneToMany(mappedBy = "conference")
    private List<ConferenceUserRoles> conferenceUserRoles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "acronym")
    private String acronym;
    @Column(name = "webPage")
    private String webPage;

    @Column(name = "expirationDate")
    private Date expirationDate;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;


    // Conference - Submission
    @OneToMany(mappedBy = "conference")
    private List<Submission> submissions;

    public Conference() {
        this.submissions = new ArrayList<>();
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<ConferenceUserRoles> getConferenceUserRoles() {
        return conferenceUserRoles;
    }

    public void setConferenceUserRoles(List<ConferenceUserRoles> conferenceUserRoles) {
        this.conferenceUserRoles = conferenceUserRoles;
    }
}

package com.nikolaev.conference_request;

import com.nikolaev.conference_request.comment.ConferenceRequestComment;
import com.nikolaev.conference_request.status.ConferenceRequestStatus;
import com.nikolaev.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "conference_request")
public class ConferenceRequest {
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

    // ConferenceRequest - User(organizer)
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;


    @OneToMany(mappedBy = "request")
    private List<ConferenceRequestComment> comments;

    //ConferenceRequest - Status
    @ManyToOne
    @JoinColumn(name = "status_id")
    private ConferenceRequestStatus status;

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

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
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

    public List<ConferenceRequestComment> getComments() {
        return comments;
    }

    public void setComments(List<ConferenceRequestComment> comments) {
        this.comments = comments;
    }

    public ConferenceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ConferenceRequestStatus status) {
        this.status = status;
    }
}

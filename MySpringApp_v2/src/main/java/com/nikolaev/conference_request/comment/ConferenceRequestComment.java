package com.nikolaev.conference_request.comment;

import com.nikolaev.conference_request.ConferenceRequest;
import com.nikolaev.conference_request.status.ConferenceRequestStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "conference_request_comment")
public class ConferenceRequestComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private ConferenceRequest request;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ConferenceRequestStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ConferenceRequest getRequest() {
        return request;
    }

    public void setRequest(ConferenceRequest request) {
        this.request = request;
    }

    public ConferenceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ConferenceRequestStatus status) {
        this.status = status;
    }
}

package com.nikolaev.conference_request.status;


import com.nikolaev.conference_request.ConferenceRequest;
import com.nikolaev.conference_request.comment.ConferenceRequestComment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conference_request_status")
public class ConferenceRequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ConferenceRequestStatusName name;

    @OneToMany(mappedBy = "status")
    private List<ConferenceRequest> conferenceRequests;

    @OneToMany(mappedBy = "status")
    private List<ConferenceRequestComment> requestComments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConferenceRequestStatusName getName() {
        return name;
    }

    public void setName(ConferenceRequestStatusName name) {
        this.name = name;
    }

    public List<ConferenceRequest> getConferenceRequests() {
        return conferenceRequests;
    }

    public void setConferenceRequests(List<ConferenceRequest> conferenceRequests) {
        this.conferenceRequests = conferenceRequests;
    }

    public List<ConferenceRequestComment> getRequestComments() {
        return requestComments;
    }
}

package com.nikolaev.submission;

import com.nikolaev.conference.Conference;
import com.nikolaev.document.Document;
import com.nikolaev.review.Review;
import com.nikolaev.submission.status.SubmissionStatus;
import com.nikolaev.submission_user_roles.SubmissionUserRoles;
import com.nikolaev.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submission")
public class Submission {

    @OneToMany(mappedBy = "submission")
    private List<SubmissionUserRoles> submissionUserRoles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "reviewable")
    private boolean reviewable;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

    // Submission - Document
    @OneToMany(mappedBy = "submission")
    private List<Document> documents;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;


    // Submission - Status
    @ManyToOne
    @JoinColumn(name = "status_id")
    private SubmissionStatus status;

    public Submission() {
        this.documents = new ArrayList<>();

    }

    // Getters and Setters

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

    public boolean isReviewable() {
        return reviewable;
    }

    public void setReviewable(boolean reviewable) {
        this.reviewable = reviewable;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }

    public List<SubmissionUserRoles> getSubmissionUserRoles() {
        return submissionUserRoles;
    }

    public void setSubmissionUserRoles(List<SubmissionUserRoles> submissionUserRoles) {
        this.submissionUserRoles = submissionUserRoles;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

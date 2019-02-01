package com.nikolaev.review;

import com.nikolaev.document.Document;
import com.nikolaev.review.status.ReviewStatus;
import com.nikolaev.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "submitted")
    private boolean submitted;

    @Column(name = "evaluation")
    private String evaluation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    // Review - User(author)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    // Review - Submission
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    // Submission - Status
    @ManyToOne
    @JoinColumn(name = "status_id")
    private ReviewStatus status;

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

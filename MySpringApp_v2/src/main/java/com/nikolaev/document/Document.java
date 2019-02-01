package com.nikolaev.document;

import com.nikolaev.review.Review;
import com.nikolaev.submission.Submission;
import com.nikolaev.submission.status.SubmissionStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fileName")
    private String filename;

    @Column(name = "fileData")
    private byte[] data;

    // Submission - Status
    @ManyToOne
    @JoinColumn(name = "status_id")
    private SubmissionStatus status;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;

    // Submission - Review
    @OneToMany(mappedBy = "document")
    private List<Review> reviews;

    public Document() {
        this.reviews = new ArrayList<>();
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }
}

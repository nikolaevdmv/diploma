package com.nikolaev.submission.status;

import com.nikolaev.document.Document;
import com.nikolaev.submission.Submission;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "submission_status")
public class SubmissionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private SubmissionStatusName name;

    @OneToMany(mappedBy = "status")
    private List<Submission> submissions;

    @OneToMany(mappedBy = "status")
    private List<Document> documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubmissionStatusName getName() {
        return name;
    }

    public void setName(SubmissionStatusName name) {
        this.name = name;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}

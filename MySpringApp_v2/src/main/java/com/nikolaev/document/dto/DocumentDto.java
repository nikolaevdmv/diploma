package com.nikolaev.document.dto;

import com.nikolaev.review.dto.BriefReviewDto;

import java.util.List;

public class DocumentDto {
    private Long id;
    private String filename;
    private int status;
    private List<BriefReviewDto> reviews;
    private Long submissionId;

    public DocumentDto() {}

    public DocumentDto(Long id, String filename, int status, List<BriefReviewDto> reviews, Long submissionId) {
        this.id = id;
        this.filename = filename;
        this.status = status;
        this.reviews = reviews;
        this.submissionId = submissionId;
    }

    public Long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public int getStatus() {
        return status;
    }

    public List<BriefReviewDto> getReviews() {
        return reviews;
    }

    public Long getSubmissionId() {
        return submissionId;
    }
}

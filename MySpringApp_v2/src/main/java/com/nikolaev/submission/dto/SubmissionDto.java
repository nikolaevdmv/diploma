package com.nikolaev.submission.dto;

import com.nikolaev.document.dto.DocumentDto;
import com.nikolaev.submission.status.dto.SubmissionStatusDto;
import com.nikolaev.user.dto.BriefUserDto;

import java.util.List;

public class SubmissionDto {

    private Long id;
    private String title;
    private Boolean reviewable;
    private List<DocumentDto> documents;
    private int status;
    private BriefUserDto author;
    private List<BriefUserDto> reviewers;
    private Long conferenceId;

    public SubmissionDto() {
    }

    public SubmissionDto(Long id, String title, Boolean reviewable, List<DocumentDto> documents, int status, BriefUserDto author, List<BriefUserDto> reviewers, Long conferenceId) {
        this.id = id;
        this.title = title;
        this.reviewable = reviewable;
        this.documents = documents;
        this.status = status;
        this.author = author;
        this.reviewers = reviewers;
        this.conferenceId = conferenceId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getReviewable() {
        return reviewable;
    }

    public List<DocumentDto> getDocuments() {
        return documents;
    }

    public int getStatus() {
        return status;
    }

    public BriefUserDto getAuthor() {
        return author;
    }

    public List<BriefUserDto> getReviewers() {
        return reviewers;
    }

    public Long getConferenceId() {
        return conferenceId;
    }
}

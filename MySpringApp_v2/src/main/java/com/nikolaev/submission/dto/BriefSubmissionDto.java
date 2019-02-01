package com.nikolaev.submission.dto;

import com.nikolaev.submission.status.dto.SubmissionStatusDto;
import com.nikolaev.user.dto.BriefUserDto;

import java.util.List;

public class BriefSubmissionDto {
    private Long id;
    private String title;
    private BriefUserDto author;
    private int status;
    private List<BriefUserDto> reviewers;

    public BriefSubmissionDto() {
    }

    public BriefSubmissionDto(Long id, String title, BriefUserDto author, int status, List<BriefUserDto> reviewers) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
        this.reviewers = reviewers;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BriefUserDto getAuthor() {
        return author;
    }

    public int getStatus() {
        return status;
    }

    public List<BriefUserDto> getReviewers() {
        return reviewers;
    }
}

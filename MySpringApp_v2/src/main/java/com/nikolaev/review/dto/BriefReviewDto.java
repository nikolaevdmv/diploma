package com.nikolaev.review.dto;

import com.nikolaev.user.dto.BriefUserDto;

public class BriefReviewDto {
    private Long id;
    private String title;
    private int status;
    private boolean submitted;
    private BriefUserDto author;

    public BriefReviewDto() {}

    public BriefReviewDto(Long id, String title, int status, boolean submitted, BriefUserDto author) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.submitted = submitted;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public BriefUserDto getAuthor() {
        return author;
    }
}

package com.nikolaev.review.dto;


import com.nikolaev.user.dto.BriefUserDto;

import java.util.Date;

public class ReviewDto {
    private Long id;
    private String title;
    private boolean submitted;
    private String evaluation;
    private Date date;
    private int status;
    private BriefUserDto author;

    public ReviewDto() {}

    public ReviewDto(Long id, String title, boolean submitted, String evaluation, Date date, int status, BriefUserDto author) {
        this.id = id;
        this.title = title;
        this.submitted = submitted;
        this.evaluation = evaluation;
        this.date = date;
        this.status = status;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public Date getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public BriefUserDto getAuthor() {
        return author;
    }
}

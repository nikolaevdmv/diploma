package com.nikolaev.conference_request.comment;

import com.nikolaev.conference_request.status.ConferenceRequestStatusDto;

import java.util.Date;

public class ConferenceRequestCommentDto {
    private Long id;
    private Date date;
    private String content;
    private int status;

    public ConferenceRequestCommentDto() {
    }

    public ConferenceRequestCommentDto(Long id, Date date, String content, int status) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public int getStatus() {
        return status;
    }
}

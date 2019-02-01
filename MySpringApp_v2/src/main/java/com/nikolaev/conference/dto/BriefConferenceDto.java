package com.nikolaev.conference.dto;

import java.util.Date;

public class BriefConferenceDto {

    private Long id;
    private String title;
    private Date expirationDate;

    public BriefConferenceDto() {}

    public BriefConferenceDto(Long id, String title, Date expirationDate) {
        this.id = id;
        this.title = title;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}

package com.nikolaev.conference_request.dto;

import com.nikolaev.conference_request.status.ConferenceRequestStatusDto;
import com.nikolaev.user.dto.BriefUserDto;

public class BriefConferenceRequestDto {
    private Long id;
    private String title;
    private BriefUserDto organizer;
    private int status;


    public BriefConferenceRequestDto() {}

    public BriefConferenceRequestDto(Long id, String title, BriefUserDto organizer, int status) {
        this.id = id;
        this.title = title;
        this.organizer = organizer;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BriefUserDto getOrganizer() {
        return organizer;
    }

    public int getStatus() {
        return status;
    }
}

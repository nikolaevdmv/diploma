package com.nikolaev.conference.dto;

import com.nikolaev.submission.dto.BriefSubmissionDto;
import com.nikolaev.user.dto.BriefUserDto;

import java.util.Date;
import java.util.List;

public class ConferenceDto {
    private Long id;
    private String title;
    private String acronym;
    private String webPage;
    private Date expirationDate;
    private String city;
    private String country;
    private List<BriefSubmissionDto> submissions;
    private List<BriefUserDto> reviewers;
    private BriefUserDto organizer;

    public ConferenceDto() {
    }

    public ConferenceDto(Long id, String title, String acronym, String webPage, Date expirationDate, String city, String country, List<BriefSubmissionDto> submissions, List<BriefUserDto> reviewers, BriefUserDto organizer) {
        this.id = id;
        this.title = title;
        this.acronym = acronym;
        this.webPage = webPage;
        this.expirationDate = expirationDate;
        this.city = city;
        this.country = country;
        this.submissions = submissions;
        this.reviewers = reviewers;
        this.organizer = organizer;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getWebPage() {
        return webPage;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<BriefSubmissionDto> getSubmissions() {
        return submissions;
    }

    public List<BriefUserDto> getReviewers() {
        return reviewers;
    }

    public BriefUserDto getOrganizer() {
        return organizer;
    }
}

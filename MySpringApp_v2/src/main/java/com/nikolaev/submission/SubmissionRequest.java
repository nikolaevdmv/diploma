package com.nikolaev.submission;


import com.nikolaev.submission.status.dto.SubmissionStatusDto;

import java.io.Serializable;

public class SubmissionRequest implements Serializable {

    private String title;

    private SubmissionStatusDto status;

    public SubmissionRequest() {}


    public SubmissionRequest(String title, SubmissionStatusDto status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubmissionStatusDto getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatusDto status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SubmissionRequest{" +
                "title='" + title + '\'' +
                '}';
    }
}

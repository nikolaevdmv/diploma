package com.nikolaev.conference;

import com.nikolaev.document.Document;
import com.nikolaev.submission.Submission;
import com.nikolaev.submission.status.SubmissionStatusName;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ConferenceStatistic implements Serializable {
    Long submissionAmount;
    Long submissionAcceptedAmount;
    Long reviewAmount;
    Long documentAmount;
    Long participantAmount;

    public ConferenceStatistic(Conference conference) {
        this.submissionAmount = countSubmissionAmount(conference);
        this.submissionAcceptedAmount = countSubmissionAcceptedAmount(conference);
        this.reviewAmount = countReviewAmount(conference);
        this.documentAmount = countDocumentAmount(conference);
        this.participantAmount = countParticipantAmount(conference);
    }

    public Long getSubmissionAmount() {
        return submissionAmount;
    }

    public Long getSubmissionAcceptedAmount() {
        return submissionAcceptedAmount;
    }

    public Long getReviewAmount() {
        return reviewAmount;
    }

    public Long getDocumentAmount() {
        return documentAmount;
    }

    public Long getParticipantAmount() {
        return participantAmount;
    }

    private Long countSubmissionAmount(Conference conference) {
        return (long) conference.getSubmissions().size();
    }

    private Long countSubmissionAcceptedAmount(Conference conferece) {
        return conferece.getSubmissions().stream()
                .filter(submission -> submission.getStatus().getName().equals(SubmissionStatusName.ACCEPT)).count();
    }

    private Long countReviewAmount(Conference conference) {
        return conference.getSubmissions().stream()
                .map(submission -> submission.getDocuments().stream().map(document -> document.getReviews()).mapToLong(value -> value.size()).sum())
                .mapToLong(Long::longValue).sum();

    }


    private Long countDocumentAmount(Conference conference) {
        return conference.getSubmissions().stream().map(submission -> submission.getDocuments().size()).mapToLong(Integer::longValue).sum();
    }

    private Long countParticipantAmount(Conference conference) {
       return conference.getConferenceUserRoles().stream().map(conferenceUserRoles -> conferenceUserRoles.getUser()).count();
    }
}

package com.nikolaev.submission;

import com.nikolaev.document.dto.DocumentDto;
import com.nikolaev.submission.dto.BriefSubmissionDto;
import com.nikolaev.submission.dto.SubmissionDto;
import com.nikolaev.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface SubmissionService {
    void save(MultipartFile file, SubmissionDto request, Long conferenceId) throws IOException;

    List<SubmissionDto> getAll(boolean reviewable);

    SubmissionDto getSubmission(Long id);

    SubmissionDto setOnReview(Long submissionId, Boolean reviewable);

    SubmissionDto uploadDocument(Long submissionId, MultipartFile file) throws IOException;

    void addReviewers(Long submissionId, List<Long> reviewers);

    void deleteReviewers(Long submissionId, List<Long> reviewers);

    List<DocumentDto> getDocuments(Long submissionId);

    Page<BriefSubmissionDto> findSubmissionsByConferenceId(Long conferenceId, Pageable pageable);


    Page<BriefSubmissionDto> getUserSubmissionsByConferenceId(Long conferenceId, Long userId, Pageable pageable);

    Page<BriefSubmissionDto> getReviewerSubmissionsByConferenceId(Long conferenceId, Long userId, Pageable pageable);

    Page<BriefSubmissionDto> findSubmissionsByConferenceIdAndStatus(Long conferenceId, Integer statusNumber, Pageable pageable);

    Page<BriefSubmissionDto> getUserSubmissionsByConferenceIdAndStatus(Long conferenceId, Long userId, Integer statusNumber, Pageable pageable);

    Page<BriefSubmissionDto> getReviewerSubmissionsByConferenceIdAndStatus(Long conferenceId, Long reviewerId, Integer statusNumber, Pageable pageable);
}

package com.nikolaev.submission.status.dto;

import com.nikolaev.submission.status.SubmissionStatusName;
import com.nikolaev.submission.status.SubmissionStatusRepository;
import com.nikolaev.submission.status.SubmissionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class SubmissionStatusMapper {
    private static SubmissionStatusRepository submissionStatusRepository;

    @Autowired
    private SubmissionStatusMapper(SubmissionStatusRepository submissionStatusRepository) {
        SubmissionStatusMapper.submissionStatusRepository = submissionStatusRepository;
    }

    public static SubmissionStatusDto toDto(SubmissionStatus status) {
        return SubmissionStatusDto.valueOf(status.getName().toString());
    }

    public static SubmissionStatus toEntity(SubmissionStatusDto dto) {
        return submissionStatusRepository.findByName(SubmissionStatusName.valueOf(dto.name()));
    }
}

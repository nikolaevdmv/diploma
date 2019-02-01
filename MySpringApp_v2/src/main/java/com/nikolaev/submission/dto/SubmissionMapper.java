package com.nikolaev.submission.dto;

import com.nikolaev.document.dto.DocumentMapper;
import com.nikolaev.conference_role.ConferenceRole;

import com.nikolaev.conference_role.ConferenceRoleName;
import com.nikolaev.submission.Submission;
import com.nikolaev.submission.status.SubmissionStatus;
import com.nikolaev.submission.status.dto.SubmissionStatusMapper;
import com.nikolaev.submission_role.SubmissionRoleName;
import com.nikolaev.submission_user_roles.SubmissionUserRoles;
import com.nikolaev.user.dto.BriefUserDto;
import com.nikolaev.user.dto.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class SubmissionMapper {
    private static final Logger logger = LoggerFactory.getLogger(SubmissionMapper.class);

    private SubmissionMapper() {
    }

    public static SubmissionDto toDto(Submission entity) {
        return new SubmissionDto(
                entity.getId(),
                entity.getTitle(),
                entity.isReviewable(),
                DocumentMapper.toListDto(entity.getDocuments()),
                mapStatus(entity.getStatus()),
                mapAuthor(entity),
                mapReviewers(entity),
                entity.getConference().getId()
        );
    }

    private static int mapStatus(SubmissionStatus status) {
        return status.getName().getValue();
    }

    public static BriefSubmissionDto toBriefDto(Submission entity) {
        return new BriefSubmissionDto(
                entity.getId(),
                entity.getTitle(),
                mapAuthor(entity),
                entity.getStatus().getName().getValue(),
                mapReviewers(entity)
        );
    }

    public static List<SubmissionDto> toListDto(List<Submission> conferences) {
        return conferences.stream().map(SubmissionMapper::toDto).collect(Collectors.toList());
    }

    public static List<BriefSubmissionDto> toListBriefDto(List<Submission> conferences) {
        return conferences.stream().map(SubmissionMapper::toBriefDto).collect(Collectors.toList());
    }


    private static BriefUserDto mapAuthor(Submission entity) {
        List<SubmissionUserRoles> userRoles = entity.getSubmissionUserRoles();
        for (SubmissionUserRoles userRole : userRoles) {
            if (userRole.getRole().getName().equals(SubmissionRoleName.AUTHOR))
                return UserMapper.toBriefDto(userRole.getUser());
        }
        return null;
    }

    private static List<BriefUserDto> mapReviewers(Submission entity) {
        List<SubmissionUserRoles> userRoles = entity.getSubmissionUserRoles();
        List<BriefUserDto> reviewers = new ArrayList<>();
        for (SubmissionUserRoles userRole : userRoles) {
            if (userRole.getRole().getName().equals(SubmissionRoleName.REVIEWER))
                reviewers.add(UserMapper.toBriefDto(userRole.getUser()));
        }
        return reviewers;
    }
}

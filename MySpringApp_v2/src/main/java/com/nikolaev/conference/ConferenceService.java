package com.nikolaev.conference;

import com.nikolaev.conference.dto.BriefConferenceDto;
import com.nikolaev.conference.dto.ConferenceDto;
import com.nikolaev.conference.exception.ConferenceNotFoundException;
import com.nikolaev.conference_request.ConferenceRequest;
import com.nikolaev.conference_request.dto.ConferenceRequestDto;
import com.nikolaev.submission.dto.BriefSubmissionDto;
import com.nikolaev.user.dto.BriefUserDto;
import com.nikolaev.user.dto.BriefUserRolesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ConferenceService {
    Page<BriefConferenceDto> getAll(Pageable pageable);

    ConferenceDto getConference(Long id) throws ConferenceNotFoundException;

    void createConference(ConferenceRequestDto request);
    void createConference(ConferenceRequest request);

    List<BriefSubmissionDto> getSubmissions(Long id, Pageable pageable);

    void addUsers(Long id, List<Long> users);

    List<BriefUserRolesDto> getUsers(Long id);

    void addReviewers(Long id, List<Long> reviewers);

    List<BriefUserDto> getReviewers(Long id);


    BriefUserRolesDto changeRoles(Long conferenceId, Long userId, Set<Integer> roles);

    BriefUserRolesDto getUserRoles(Long conferenceId, Long userId);

    void inviteUser(Long conferenceId, String username);

    Page<BriefConferenceDto> findAllByStatus(Integer statusNumber, Pageable pageable);

    ConferenceStatistic getConferenceStatistic(Long conferenceId);
}

package com.nikolaev.user;


import com.nikolaev.conference.dto.BriefConferenceDto;
import com.nikolaev.conference.dto.ConferenceDto;
import com.nikolaev.conference_request.dto.BriefConferenceRequestDto;
import com.nikolaev.conference_request.dto.ConferenceRequestDto;
import com.nikolaev.review.dto.ReviewDto;
import com.nikolaev.conference_role.ConferenceRole;
import com.nikolaev.submission.dto.BriefSubmissionDto;
import com.nikolaev.submission.dto.SubmissionDto;
import com.nikolaev.user.dto.BriefUserDto;
import com.nikolaev.user.dto.BriefUserRolesDto;
import com.nikolaev.user.dto.UserDto;
import com.nikolaev.user.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto save(UserDto user);

    User findByUsername(String username) throws UserNotFoundException;

    void sendConfirmationEmail(User user);

    User confirmEmail(String token) throws UserNotFoundException;

    List<BriefConferenceDto> getUserConferences(Long id);

    List<UserDto> getReviewers();

    List<ReviewDto> getReviewList(Long id, Long conferenceId);

    boolean isReviewer(Long userId, Long conferenceId);

    Set<ConferenceRole> getUser(Long id);

    List<BriefSubmissionDto> getSubmissions(Long userId);

    Page<BriefUserDto> getUsers(Pageable pageable);

    List<BriefConferenceRequestDto> getConferenceRequests(Long id);

    Page<BriefUserRolesDto> findUsersByConferenceId(Long id, Integer roleNumber, Pageable pageable);

    Page<BriefUserDto> getReviewersBySubmissionId(Long submissionId, Pageable pageable);

    Page<BriefUserRolesDto> searchUsersByConferenceId(Long id, Integer roleNumber, String searchString, Pageable pageable);

    Page<BriefUserDto> searchUsers(String searchString, Pageable pageable);
}

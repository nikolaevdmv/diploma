package com.nikolaev.conference;

import com.nikolaev.conference.dto.BriefConferenceDto;
import com.nikolaev.conference.exception.ConferenceNotFoundException;
import com.nikolaev.conference_role.ConferenceRoleName;
import com.nikolaev.domain.ApiError;
import com.nikolaev.conference_request.dto.ConferenceRequestDto;
import com.nikolaev.submission.SubmissionService;
import com.nikolaev.submission.dto.BriefSubmissionDto;
import com.nikolaev.submission.dto.SubmissionDto;
import com.nikolaev.user.UserService;
import com.nikolaev.user.dto.BriefUserRolesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public Page<BriefConferenceDto> getConferences(@RequestParam(value = "status", required = false) Integer statusNumber,
                                                   @PageableDefault Pageable pageable) {
        if (statusNumber == null)
            return conferenceService.getAll(pageable);
        else
            return conferenceService.findAllByStatus(statusNumber, pageable);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getConference(@PathVariable("id") Long id) throws ConferenceNotFoundException {
        return ResponseEntity.ok(conferenceService.getConference(id));
    }

    @RequestMapping(value = "{id}/submissions", method = RequestMethod.GET)
    public Page<BriefSubmissionDto> getSubmissions(@PathVariable("id") Long id, @PageableDefault Pageable pageable,
                                                   @RequestParam(value = "status", required = false) Integer statusNumber) {
        if (statusNumber == null)
            return submissionService.findSubmissionsByConferenceId(id, pageable);
        else
            return submissionService.findSubmissionsByConferenceIdAndStatus(id, statusNumber, pageable);
    }

    @RequestMapping(value = "{conferenceId}/submissions/users/{userId}", method = RequestMethod.GET)
    public Page<BriefSubmissionDto> getUserSubmissions(@PathVariable("conferenceId") Long conferenceId,
                                                       @PathVariable("userId") Long userId,
                                                       @RequestParam(value = "status", required = false) Integer statusNumber,
                                                       @PageableDefault Pageable pageable) {
        if (statusNumber == null)
            return submissionService.getUserSubmissionsByConferenceId(conferenceId, userId, pageable);
        else
            return submissionService.getUserSubmissionsByConferenceIdAndStatus(conferenceId, userId, statusNumber, pageable);
    }

    @RequestMapping(value = "{conferenceId}/submissions/reviewers/{reviewerId}", method = RequestMethod.GET)
    public Page<BriefSubmissionDto> getReviewerSubmissions(@PathVariable("conferenceId") Long conferenceId,
                                                           @PathVariable("reviewerId") Long reviewerId,
                                                           @RequestParam(value = "status", required = false) Integer statusNumber,
                                                           @PageableDefault Pageable pageable) {
        if (statusNumber == null)
            return submissionService.getReviewerSubmissionsByConferenceId(conferenceId, reviewerId, pageable);
        else
            return submissionService.getReviewerSubmissionsByConferenceIdAndStatus(conferenceId, reviewerId, statusNumber, pageable);
    }

    @RequestMapping(value = "{id}/users", method = RequestMethod.GET)
    public Page<BriefUserRolesDto> getUsers(@PathVariable("id") Long id,
                                            @RequestParam(value = "role", required = false) Integer roleNumber,
                                            @RequestParam(value = "search", required = false) String searchString,
                                            @PageableDefault Pageable pageable) {
        if (searchString == null) {
            return userService.findUsersByConferenceId(id, roleNumber, pageable);
        } else if (!searchString.isEmpty()) {
            return userService.searchUsersByConferenceId(id, roleNumber, searchString, pageable);
        }
        return null;
    }

    @RequestMapping(value = "{id}/reviewers", method = RequestMethod.POST)
    public void addReviewers(@PathVariable("id") Long id, @RequestBody List<Long> reviewers) {
        conferenceService.addReviewers(id, reviewers);
    }

    @RequestMapping(value = "{id}/reviewers", method = RequestMethod.GET)
    public Page<BriefUserRolesDto> getReviewers(@PathVariable("id") Long id,
                                                @PageableDefault Pageable pageable) {
        return userService.findUsersByConferenceId(id, ConferenceRoleName.REVIEWER.getValue(), pageable);
    }

    /**
     * Creates the new submission with new document
     *
     * @param submission submission request object
     * @param file       document file
     * @param id         conference id
     */
    @RequestMapping(value = "{id}/submissions", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public void submissionUpload(@RequestPart("submission") SubmissionDto submission,
                                 @RequestPart("file") MultipartFile file,
                                 @PathVariable("id") Long id) throws IOException {
        submissionService.save(file, submission, id);

    }

    @RequestMapping(value = "{conferenceId}/users/{userId}", method = RequestMethod.PUT)
    public ResponseEntity changeRoles(@PathVariable("conferenceId") Long conferenceId,
                                      @PathVariable("userId") Long userId,
                                      @RequestBody Set<Integer> roles) {
        return ResponseEntity.ok(conferenceService.changeRoles(conferenceId, userId, roles));
    }

    @RequestMapping(value = "{conferenceId}/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUserRoles(@PathVariable("conferenceId") Long conferenceId,
                                       @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(conferenceService.getUserRoles(conferenceId, userId));
    }

    @RequestMapping(value = "{conferenceId}/invite", method = RequestMethod.POST)
    public void inviteUser(@PathVariable("conferenceId") Long conferenceId, @RequestBody String username) {
        conferenceService.inviteUser(conferenceId, username);
    }

    @RequestMapping(value = "{conferenceId}/statistic", method = RequestMethod.GET)
    public ConferenceStatistic getStatistic(@PathVariable("conferenceId") Long conferenceId) {
        return conferenceService.getConferenceStatistic(conferenceId);
    }

    @ExceptionHandler(ConferenceNotFoundException.class)
    public ResponseEntity handleConferenceNotFoundException(Exception e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                e.getMessage(),
                e.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}

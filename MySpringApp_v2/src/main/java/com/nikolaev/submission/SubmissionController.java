package com.nikolaev.submission;

import com.nikolaev.submission.dto.SubmissionDto;
import com.nikolaev.user.UserService;
import com.nikolaev.user.dto.BriefUserDto;
import com.nikolaev.user.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class SubmissionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SubmissionService submissionService;

    @Autowired
    UserService userService;



    /**
     * Returns submission by id
     *
     * @param submissionId Submission id
     * @return Submission
     */
    @RequestMapping(value = "/submissions/{submissionId}", method = RequestMethod.GET)
    public ResponseEntity getSubmission(@PathVariable("submissionId") Long submissionId) {
        logger.info("in getSubmission()");
        return ResponseEntity.ok(submissionService.getSubmission(submissionId));
    }

    /**
     * Returns all submission
     *
     * @return Submissions from specific conference
     */
    @RequestMapping(value = "/submissions", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam(value = "reviewable", required = false) boolean reviewable) {
        return ResponseEntity.ok(submissionService.getAll(reviewable));
    }


    /**
     * @param submissionId
     * @param reviewable
     * @return
     */
    @RequestMapping(value = "/submissions/{submissionId}/reviewable", method = RequestMethod.PUT)
    public ResponseEntity setReviewable(@PathVariable("submissionId") Long submissionId,
                                        @RequestBody String reviewable) {
        return ResponseEntity.ok(submissionService.setOnReview(submissionId, Boolean.valueOf(reviewable)));
    }

    /**
     * Adds reviewers to existing submission
     *
     * @param submissionId Submission id
     * @param reviewers    List reviewers
     */
    @RequestMapping(value = "/submissions/{submissionId}/reviewers", method = RequestMethod.POST)
    public void addReviewers(@PathVariable("submissionId") Long submissionId, @RequestBody List<Long> reviewers) {
        submissionService.addReviewers(submissionId, reviewers);
    }

    @RequestMapping(value = "/submissions/{submissionId}/reviewers", method = RequestMethod.GET)
    public Page<BriefUserDto> getReviewers(@PathVariable("submissionId") Long submissionId, @PageableDefault Pageable pageable) {
        return userService.getReviewersBySubmissionId(submissionId, pageable);
    }

    @RequestMapping(value = "/submissions/{submissionId}/reviewers", method = RequestMethod.DELETE)
    public void deleteReviewers(@PathVariable("submissionId") Long submissionId, @RequestBody List<Long> reviewers) {
        submissionService.deleteReviewers(submissionId, reviewers);
    }

    @RequestMapping(value = "/submissions/{submissionId}/documents", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public ResponseEntity uploadDocument(@PathVariable("submissionId") Long submissionId, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(submissionService.uploadDocument(submissionId, file));
    }

    @RequestMapping(value = "/submissions/{submissionId}/documents", method = RequestMethod.GET)
    public ResponseEntity getDocuments(@PathVariable("submissionId") Long submissionId) {
        return ResponseEntity.ok(submissionService.getDocuments(submissionId));
    }




}

package com.nikolaev.conference_request;

import com.nikolaev.conference_request.comment.ConferenceRequestCommentDto;
import com.nikolaev.conference_request.dto.BriefConferenceRequestDto;
import com.nikolaev.conference_request.dto.ConferenceRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class ConferenceRequestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConferenceRequestService conferenceRequestService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createRequest(@RequestBody ConferenceRequestDto requestDTO) {
        logger.info("createRequest() is invoked");
        logger.info(requestDTO.toString());
        conferenceRequestService.createRequest(requestDTO);
        return ResponseEntity.ok(requestDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<BriefConferenceRequestDto> getAll(@RequestParam(value = "status", required = false) Integer statusNumber,
                                                  @PageableDefault Pageable pageable) {
        return conferenceRequestService.getAll(statusNumber, pageable);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody ConferenceRequestDto requestDTO) {
        conferenceRequestService.update(requestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getRequest(@PathVariable("id") Long requestId) {
        return ResponseEntity.ok(conferenceRequestService.getRequest(requestId));
    }

    @RequestMapping(value = "{id}/comments", method = RequestMethod.POST)
    public ResponseEntity createComment(@RequestBody ConferenceRequestCommentDto commentDto,
                                        @PathVariable("id") Long requestId) {
        return ResponseEntity.ok(conferenceRequestService.createComment(requestId, commentDto));
    }
}

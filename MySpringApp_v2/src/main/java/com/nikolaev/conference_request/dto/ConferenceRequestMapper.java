package com.nikolaev.conference_request.dto;


import com.nikolaev.conference_request.ConferenceRequest;
import com.nikolaev.conference_request.comment.ConferenceRequestComment;
import com.nikolaev.conference_request.comment.ConferenceRequestCommentDto;
import com.nikolaev.conference_request.status.ConferenceRequestStatus;
import com.nikolaev.conference_request.status.ConferenceRequestStatusDto;
import com.nikolaev.user.dto.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ConferenceRequestMapper {

    private ConferenceRequestMapper() {
    }

    public static ConferenceRequestDto toDto(ConferenceRequest entity) {
        return new ConferenceRequestDto(
                entity.getId(),
                entity.getTitle(),
                entity.getAcronym(),
                entity.getWebPage(),
                UserMapper.toBriefDto(entity.getOrganizer()),
                entity.getCity(),
                entity.getCountry(),
                mapComments(entity.getComments()),
                mapStatus(entity.getStatus()),
                entity.getExpirationDate()
        );
    }

    public static BriefConferenceRequestDto toBriefDto(ConferenceRequest entity) {
        return new BriefConferenceRequestDto(
                entity.getId(),
                entity.getTitle(),
                UserMapper.toBriefDto(entity.getOrganizer()),
                mapStatus(entity.getStatus())
        );
    }

    public static List<BriefConferenceRequestDto> toListBriefDto(List<ConferenceRequest> requests) {
        return requests.stream().map(ConferenceRequestMapper::toBriefDto).collect(Collectors.toList());
    }

    public static List<ConferenceRequestDto> toListDto(List<ConferenceRequest> requests) {
        return requests.stream().map(ConferenceRequestMapper::toDto).collect(Collectors.toList());
    }

    private static int mapStatus(ConferenceRequestStatus status) {
//        return ConferenceRequestStatusDto.valueOf(status.getName().toString());
        return status.getName().getValue();
    }

    private static List<ConferenceRequestCommentDto> mapComments(List<ConferenceRequestComment> comments) {
        return comments.stream().map(conferenceRequestComment -> new ConferenceRequestCommentDto(
                conferenceRequestComment.getId(),
                conferenceRequestComment.getDate(),
                conferenceRequestComment.getContent(),
                mapStatus(conferenceRequestComment.getStatus())
        )).collect(Collectors.toList());
    }

}

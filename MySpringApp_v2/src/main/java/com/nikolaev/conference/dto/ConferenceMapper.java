package com.nikolaev.conference.dto;

import com.nikolaev.conference.Conference;
import com.nikolaev.conference_user_roles.ConferenceUserRoles;
import com.nikolaev.conference_role.*;
import com.nikolaev.submission.dto.SubmissionMapper;
import com.nikolaev.user.dto.BriefUserDto;
import com.nikolaev.user.dto.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConferenceMapper {

    private final static Logger logger = LoggerFactory.getLogger(UserMapper.class);

    private ConferenceMapper() {
    }

    public static ConferenceDto toDto(Conference entity) {
        return new ConferenceDto(
                entity.getId(),
                entity.getTitle(),
                entity.getAcronym(),
                entity.getWebPage(),
                entity.getExpirationDate(),
                entity.getCity(),
                entity.getCountry(),
                SubmissionMapper.toListBriefDto(entity.getSubmissions()),
                mapReviewers(entity.getConferenceUserRoles()),
                mapOrganizer(entity.getConferenceUserRoles())
        );
    }

    public static BriefConferenceDto toBriefDto(Conference entity) {
        return new BriefConferenceDto(
                entity.getId(),
                entity.getTitle(),
                entity.getExpirationDate()
        );
    }


    public static List<ConferenceDto> toListDto(List<Conference> conferences) {
        return conferences.stream().map(ConferenceMapper::toDto).collect(Collectors.toList());
    }

    public static List<BriefConferenceDto> toListBriefDto(List<Conference> conferences) {
        return conferences.stream().map(ConferenceMapper::toBriefDto).collect(Collectors.toList());
    }



    private static Map<String, List<ConferenceRoleName>> mapUsers(List<ConferenceUserRoles> conferenceUserRoles) {
        Map<String, List<ConferenceRoleName>> users = new HashMap<>();
        for (ConferenceUserRoles conferenceUserRole : conferenceUserRoles) {
            List<ConferenceRoleName> roles = conferenceUserRole.getRoleList().getRoles().stream().map(ConferenceRole::getName).collect(Collectors.toList());
            users.put(conferenceUserRole.getUser().getUsername(), roles);
        }
        return users;
    }

    private static List<BriefUserDto> mapReviewers(List<ConferenceUserRoles> conferenceUserRoles) {
        List<BriefUserDto> reviewers = new ArrayList<>();
        for (ConferenceUserRoles conferenceUserRole : conferenceUserRoles) {
            List<ConferenceRoleName> roles = conferenceUserRole.getRoleList().getRoles().stream().map(ConferenceRole::getName).collect(Collectors.toList());
            if (roles.contains(ConferenceRoleName.REVIEWER))
                reviewers.add(UserMapper.toBriefDto(conferenceUserRole.getUser()));
        }
        return reviewers;
    }

    private static BriefUserDto mapOrganizer(List<ConferenceUserRoles> conferenceUserRoles) {
        for (ConferenceUserRoles conferenceUserRole : conferenceUserRoles) {
            List<ConferenceRoleName> roles = conferenceUserRole.getRoleList().getRoles().stream().map(ConferenceRole::getName).collect(Collectors.toList());
            if (roles.contains(ConferenceRoleName.CREATOR))
                return UserMapper.toBriefDto(conferenceUserRole.getUser());
        }
        return null;
    }

}

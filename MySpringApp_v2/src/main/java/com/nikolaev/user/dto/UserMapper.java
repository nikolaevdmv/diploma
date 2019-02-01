package com.nikolaev.user.dto;

import com.nikolaev.conference_role.ConferenceRole;
import com.nikolaev.conference_role.ConferenceRoleName;
import com.nikolaev.conference_user_roles.ConferenceUserRoles;
import com.nikolaev.security.JwtUser;
import com.nikolaev.user.User;
import com.nikolaev.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private final static Logger logger = LoggerFactory.getLogger(UserMapper.class);


    private UserMapper() {
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.isEnabled()
        );
    }

    public static UserDto toDto(UserDetails userDetails) {
        if (userDetails instanceof JwtUser) {
            JwtUser user = (JwtUser) userDetails;
            return new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    user.isEnabled()
            );
        } else
            return null;
    }

    public static BriefUserDto toBriefDto(User user) {
        return new BriefUserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname()
        );
    }

    public static BriefUserRolesDto toBriefRolesDto(User user, Long conferenceId) {
        return new BriefUserRolesDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                mapConferenceUserRoles(user, conferenceId)
        );
    }

    private static List<Integer> mapConferenceUserRoles(User user, Long conferenceId) {
        for (ConferenceUserRoles userRoles : user.getConferenceUserRoles()) {
            if (userRoles.getConference().getId().equals(conferenceId))
                return userRoles.getRoleList().getRoles().stream().map(ConferenceRole::getName).map(ConferenceRoleName::getValue).collect(Collectors.toList());
        }
        return null;
    }

    public static List<UserDto> toListDto(List<User> users) {
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public static List<BriefUserDto> toListBriefDto(List<User> users) {
        return users.stream().map(UserMapper::toBriefDto).collect(Collectors.toList());
    }

}

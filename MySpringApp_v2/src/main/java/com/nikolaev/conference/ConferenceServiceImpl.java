package com.nikolaev.conference;

import com.nikolaev.conference.dto.BriefConferenceDto;
import com.nikolaev.conference.dto.ConferenceDto;
import com.nikolaev.conference.dto.ConferenceMapper;
import com.nikolaev.conference.exception.ConferenceNotFoundException;
import com.nikolaev.conference_request.ConferenceRequest;
import com.nikolaev.conference_user_roles.ConferenceUserRoles;
import com.nikolaev.conference_request.dto.ConferenceRequestDto;
import com.nikolaev.conference_user_roles.ConferenceUserRolesRepository;
import com.nikolaev.conference_role.*;
import com.nikolaev.submission.dto.BriefSubmissionDto;
import com.nikolaev.submission.dto.SubmissionMapper;
import com.nikolaev.user.User;
import com.nikolaev.user.UserRepository;
import com.nikolaev.user.UserService;
import com.nikolaev.user.dto.BriefUserDto;
import com.nikolaev.user.dto.BriefUserRolesDto;
import com.nikolaev.user.dto.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleList;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    ConferenceRoleListHolderRepository roleListHolderRepository;

    @Autowired
    ConferenceRoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConferenceUserRolesRepository conferenceUserRolesRepository;

    @Override
    public Page<BriefConferenceDto> getAll(Pageable pageable) {
        return conferenceRepository.findAll(pageable).map(ConferenceMapper::toBriefDto);
    }


    @Override
    public ConferenceDto getConference(Long id) throws ConferenceNotFoundException {
        Conference conference = conferenceRepository.getOne(id);
        if (conference == null)
            throw new ConferenceNotFoundException("Conference not found");
        else
            return ConferenceMapper.toDto(conference);
    }

    @Override
    public void createConference(ConferenceRequestDto request) {

    }

    public void createConference(ConferenceRequest request) {
        Conference conference = new Conference();
        User user = userRepository.findByUsername(request.getOrganizer().getUsername());
        conference.setTitle(request.getTitle());
        conference.setAcronym(request.getAcronym());
        conference.setWebPage(request.getWebPage());
        conference.setCity(request.getCity());
        conference.setCountry(request.getCountry());
        conference.setExpirationDate(request.getExpirationDate());
        ConferenceUserRoles conferenceUserRoles = new ConferenceUserRoles();
        ConferenceRole creator = roleRepository.findByName(ConferenceRoleName.CREATOR);
        ConferenceRoleListHolder roleListHolder = new ConferenceRoleListHolder();
        Set<ConferenceRole> roles = new HashSet<ConferenceRole>() {{
            add(creator);
        }};
        roleListHolder.setRoles(roles);
        conferenceUserRoles.setConference(conference);
        conferenceUserRoles.setRoleList(roleListHolder);
        conferenceUserRoles.setUser(user);

        conferenceRepository.save(conference);
        roleListHolderRepository.save(roleListHolder);
        conferenceUserRolesRepository.save(conferenceUserRoles);
    }

    @Override
    public List<BriefUserDto> getReviewers(Long id) {
        Conference conference = conferenceRepository.getOne(id);
        List<User> userList = new ArrayList<>();
        for (ConferenceUserRoles conferenceUserRoles : conference.getConferenceUserRoles()) {
            List<ConferenceRoleName> roles =
                    conferenceUserRoles.getRoleList().getRoles().stream().map(ConferenceRole::getName).collect(Collectors.toList());
            if (roles.contains(ConferenceRoleName.REVIEWER))
                userList.add(conferenceUserRoles.getUser());
        }
        return userList.stream().map(UserMapper::toBriefDto).collect(Collectors.toList());
    }

    @Override
    public BriefUserRolesDto changeRoles(Long conferenceId, Long userId, Set<Integer> roles) {

        ConferenceUserRoles userRoles = conferenceUserRolesRepository.findByConferenceIdAndUserId(conferenceId, userId);
        ConferenceRoleListHolder roleList = userRoles.getRoleList();


        Set<ConferenceRole> newRoleList =
                roles.stream().map(ConferenceRoleName::fromInt).map(roleRepository::findByName).collect(Collectors.toSet());
        roleList.setRoles(newRoleList);
        roleListHolderRepository.save(roleList);

        return UserMapper.toBriefRolesDto(userRepository.getOne(userId), conferenceId);
    }

    @Override
    public BriefUserRolesDto getUserRoles(Long conferenceId, Long userId) {
        Conference conference = conferenceRepository.getOne(conferenceId);
        for (ConferenceUserRoles userRoles : conference.getConferenceUserRoles()) {
            if (userRoles.getUser().getId().equals(userId)) {
                return UserMapper.toBriefRolesDto(userRoles.getUser(), conferenceId);
            }
        }
        return null;
    }

    @Override
    public void inviteUser(Long conferenceId, String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (user.getConferenceUserRoles()
                    .stream()
                    .anyMatch(conferenceUserRoles -> conferenceUserRoles.getConference().getId().equals(conferenceId))) {
                return;
            }

            ConferenceRoleListHolder listHolder = new ConferenceRoleListHolder();
            listHolder.setRoles(new HashSet<ConferenceRole>() {{
                add(roleRepository.findByName(ConferenceRoleName.SUBMITTER));
            }});
            roleListHolderRepository.save(listHolder);

            Conference conference = conferenceRepository.getOne(conferenceId);
            ConferenceUserRoles conferenceUserRoles = new ConferenceUserRoles();
            conferenceUserRoles.setConference(conference);
            conferenceUserRoles.setUser(user);
            conferenceUserRoles.setRoleList(listHolder);
            conferenceUserRolesRepository.save(conferenceUserRoles);


        }
    }

    @Override
    public Page<BriefConferenceDto> findAllByStatus(Integer statusNumber, Pageable pageable) {
        if (statusNumber.equals(0)) {
            return conferenceRepository.findAllActive(new Date(), pageable).map(ConferenceMapper::toBriefDto);
        } else if (statusNumber.equals(1)) {
            return conferenceRepository.findAllCompleted(new Date(), pageable).map(ConferenceMapper::toBriefDto);
        }
        return null;
    }

    @Override
    public ConferenceStatistic getConferenceStatistic(Long conferenceId) {
        return new ConferenceStatistic(conferenceRepository.getOne(conferenceId));
    }

    @Override
    public void addUsers(Long id, List<Long> users) {
        Conference conference = conferenceRepository.getOne(id);
        Set<User> userList = users.stream().map(userRepository::getOne).collect(Collectors.toSet());

        for (User user : userList) {
            ConferenceRoleListHolder listHolder = new ConferenceRoleListHolder();
            listHolder.setRoles(new HashSet<ConferenceRole>() {{
//                add(participant);
            }});

            ConferenceUserRoles userRoles = new ConferenceUserRoles();
            userRoles.setUser(user);
            userRoles.setConference(conference);
            userRoles.setRoleList(listHolder);

            roleListHolderRepository.save(listHolder);
            conferenceUserRolesRepository.save(userRoles);
        }

    }

    @Override
    public List<BriefUserRolesDto> getUsers(Long id) {
        Conference conference = conferenceRepository.getOne(id);
        List<User> userList = new ArrayList<>();
        conference.getConferenceUserRoles().forEach(userRole -> userList.add(userRole.getUser()));
        return userList.stream().map(user -> UserMapper.toBriefRolesDto(user, id)).collect(Collectors.toList());
    }

    @Override
    public void addReviewers(Long id, List<Long> reviewers) {
        List<User> userList = reviewers.stream().map(userRepository::getOne).collect(Collectors.toList());

        ConferenceRole reviewerRole = roleRepository.findByName(ConferenceRoleName.REVIEWER);
//        ConferenceRole participantRole = roleRepository.findByName(ConferenceRoleName.PARTICIPANT);

        for (User user : userList) {
            for (ConferenceUserRoles userRoles : user.getConferenceUserRoles()) {
                ConferenceRoleListHolder roleList = userRoles.getRoleList();
                Set<ConferenceRole> roles = roleList.getRoles();
                if (roles.contains(reviewerRole))
                    break;
//                roles.remove(participantRole);
                roles.add(reviewerRole);
                roleList.setRoles(roles);
                roleListHolderRepository.save(roleList);
            }
        }

    }

    @Override
    public List<BriefSubmissionDto> getSubmissions(Long id, Pageable pageable) {
        return SubmissionMapper.toListBriefDto(conferenceRepository.getOne(id).getSubmissions());
    }


}

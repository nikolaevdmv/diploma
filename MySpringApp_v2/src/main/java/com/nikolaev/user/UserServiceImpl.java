package com.nikolaev.user;


import com.nikolaev.conference.Conference;
import com.nikolaev.conference.dto.BriefConferenceDto;
import com.nikolaev.conference.dto.ConferenceMapper;
import com.nikolaev.conference_request.dto.BriefConferenceRequestDto;
import com.nikolaev.conference_request.dto.ConferenceRequestDto;
import com.nikolaev.conference_request.dto.ConferenceRequestMapper;
import com.nikolaev.conference_user_roles.ConferenceUserRoles;
import com.nikolaev.conference.dto.ConferenceDto;
import com.nikolaev.conference_user_roles.ConferenceUserRolesRepository;
import com.nikolaev.review.dto.ReviewDto;
import com.nikolaev.review.dto.ReviewMapper;
import com.nikolaev.conference_role.ConferenceRole;
import com.nikolaev.conference_role.ConferenceRoleName;
import com.nikolaev.submission.Submission;
import com.nikolaev.submission.dto.BriefSubmissionDto;
import com.nikolaev.submission.dto.SubmissionDto;
import com.nikolaev.submission.dto.SubmissionMapper;
import com.nikolaev.submission_role.SubmissionRoleName;
import com.nikolaev.submission_user_roles.SubmissionUserRoles;
import com.nikolaev.user.dto.BriefUserDto;
import com.nikolaev.user.dto.BriefUserRolesDto;
import com.nikolaev.user.dto.UserDto;
import com.nikolaev.user.dto.UserMapper;
import com.nikolaev.user.exception.UserNotFoundException;
import com.nikolaev.security.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ConferenceUserRolesRepository conferenceUserRolesRepository;


    @Override
    public UserDto save(UserDto user) {
        logger.debug("in save()");
        return null;
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException("User not found");
        else
            return user;
    }


    public void sendConfirmationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Welcome");
        message.setText("Welcome to website. Please, confirm your email.\n" + generateConfirmationUrl(user));
        emailSender.send(message);
    }

    @Override
    public User confirmEmail(String token) throws UserNotFoundException {
        User user = userRepository.findByConfirmationToken(token);

        if (user != null) {
            user.setConfirmationToken("");
            user.setConfirmed(true);
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public List<BriefConferenceDto> getUserConferences(Long id) {
        User user = userRepository.getOne(id);
        List<Conference> conferenceList = new ArrayList<>();
        user.getConferenceUserRoles().forEach(conferenceUserRoles -> conferenceList.add(conferenceUserRoles.getConference()));
        return ConferenceMapper.toListBriefDto(conferenceList);
    }

    @Override
    public List<UserDto> getReviewers() {
        Authority authority = authorityRepository.findByName(AuthorityName.ROLE_REVIEWER);
        List<User> reviewers = userRepository.findByAuthorities(Arrays.asList(authority));
        return UserMapper.toListDto(reviewers);
    }

    @Override
    public List<ReviewDto> getReviewList(Long id, Long conferenceId) {
        return null;
//        if (conferenceId == null) {
//            return reviewMapper.toListDto(userRepository.getOne(id).getReviews());
//        } else {
//            return reviewMapper.toListDto(userRepository.getOne(id).getReviews().stream().filter(review ->
//                    review.getSubmission().getConference().getId().equals(conferenceId))
//                    .collect(Collectors.toList()));
//        }
    }

    @Override
    public boolean isReviewer(Long userId, Long conferenceId) {
        User user = userRepository.getOne(userId);
        for (ConferenceUserRoles conferenceUserRoles : user.getConferenceUserRoles()) {
            if (conferenceUserRoles.getConference().getId().equals(conferenceId)) {
                for (ConferenceRole role : conferenceUserRoles.getRoleList().getRoles()) {
                    if (role.getName().equals(ConferenceRoleName.REVIEWER))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<ConferenceRole> getUser(Long id) {
        User user = userRepository.getOne(id);
        Set<ConferenceRole> list = new HashSet<>();
        for (ConferenceUserRoles conferenceUserRoles : user.getConferenceUserRoles()) {
            list = conferenceUserRoles.getRoleList().getRoles();
        }
        return list;
    }


    @Override
    public List<BriefSubmissionDto> getSubmissions(Long userId) {
        User user = userRepository.getOne(userId);
        List<Submission> submissions = new ArrayList<>();
        user.getSubmissionUserRoles().forEach(submissionUserRoles -> submissions.add(submissionUserRoles.getSubmission()));
        return SubmissionMapper.toListBriefDto(submissions);
    }

    @Override
    public Page<BriefUserDto> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper::toBriefDto);
    }

    @Override
    public List<BriefConferenceRequestDto> getConferenceRequests(Long id) {
        return ConferenceRequestMapper.toListBriefDto(userRepository.getOne(id).getRequests());
    }

    @Override
    public Page<BriefUserRolesDto> findUsersByConferenceId(Long id, Integer roleNumber, Pageable pageable) {
        if (roleNumber != null)
            return userRepository
                    .findAllByConferenceIdAndRoleName(id, ConferenceRoleName.fromInt(roleNumber), pageable)
                    .map(user -> UserMapper.toBriefRolesDto(user, id));
        else
            return userRepository
                    .findAllByConferenceId(id, pageable)
                    .map(user -> UserMapper.toBriefRolesDto(user, id));
    }

    @Override
    public Page<BriefUserDto> getReviewersBySubmissionId(Long submissionId, Pageable pageable) {
        return userRepository.findAllBySubmissionIdAndRoleName(submissionId, SubmissionRoleName.REVIEWER, pageable)
                .map(UserMapper::toBriefDto);
    }

    @Override
    public Page<BriefUserRolesDto> searchUsersByConferenceId(Long id, Integer roleNumber, String searchString, Pageable pageable) {
        return userRepository.searchByConferenceIdAndRoleName(id, ConferenceRoleName.fromInt(roleNumber), searchString, pageable)
                .map(user -> UserMapper.toBriefRolesDto(user, id));
    }

    @Override
    public Page<BriefUserDto> searchUsers(String searchString, Pageable pageable) {
        return userRepository.searchAll(searchString, pageable).map(UserMapper::toBriefDto);
    }

    private String generateConfirmationUrl(User user) {
        String host = env.getProperty("host");
        return host + "/confirmation/" + user.getConfirmationToken();
    }


}

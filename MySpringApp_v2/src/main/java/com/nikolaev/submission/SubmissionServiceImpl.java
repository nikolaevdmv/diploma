package com.nikolaev.submission;

import com.nikolaev.conference.Conference;
import com.nikolaev.conference.ConferenceRepository;
import com.nikolaev.document.Document;
import com.nikolaev.document.DocumentRepository;
import com.nikolaev.document.dto.DocumentDto;
import com.nikolaev.document.dto.DocumentMapper;
import com.nikolaev.review.Review;
import com.nikolaev.review.ReviewRepository;
import com.nikolaev.conference_role.*;
import com.nikolaev.submission.dto.BriefSubmissionDto;
import com.nikolaev.submission.dto.SubmissionDto;
import com.nikolaev.submission.dto.SubmissionMapper;
import com.nikolaev.submission.status.SubmissionStatus;
import com.nikolaev.submission.status.SubmissionStatusName;
import com.nikolaev.submission.status.SubmissionStatusRepository;
import com.nikolaev.submission_role.SubmissionRole;
import com.nikolaev.submission_role.SubmissionRoleName;
import com.nikolaev.submission_role.SubmissionRoleRepository;
import com.nikolaev.submission_user_roles.SubmissionUserRoles;
import com.nikolaev.submission_user_roles.SubmissionUserRolesRepository;
import com.nikolaev.user.User;
import com.nikolaev.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    //    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static Logger logger = LoggerFactory.getLogger(SubmissionServiceImpl.class);

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    SubmissionRoleRepository submissionRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    SubmissionStatusRepository submissionStatusRepository;

    @Autowired
    SubmissionUserRolesRepository submissionUserRolesRepository;

    @Autowired
    SubmissionRoleRepository roleRepository;

    @Autowired
    ConferenceRoleListHolderRepository roleListHolderRepository;

    @Override
    public void save(MultipartFile file, SubmissionDto submissionDto, Long conferenceId) throws IOException {
        logger.debug("SubmissionService: save() is invoked");
        logger.debug("SubmissionService: save(): " + submissionDto.toString());

        // Fetching author
        User user = userRepository.findByUsername(submissionDto.getAuthor().getUsername());


        Submission submission = new Submission();
        Document document = new Document();

        Conference conference = conferenceRepository.getOne(conferenceId);

        submission.setTitle(submissionDto.getTitle());
        submission.setConference(conference);
        submission.setReviewable(false);

        SubmissionStatus status = submissionStatusRepository.findByName(SubmissionStatusName.fromInt(submissionDto.getStatus()));
        if (status == null) {
            status = submissionStatusRepository.findByName(SubmissionStatusName.PENDING);
        }
        submission.setStatus(status);
        logger.debug("getOriginalFilename(): " + file.getOriginalFilename());
        document.setFilename(   file.getOriginalFilename());
        document.setSubmission(submission);
        document.setData(file.getBytes());
        document.setStatus(submissionStatusRepository.findByName(SubmissionStatusName.PENDING));

//        List<Document> documents = submission.getDocuments();
//        documents.add(document);
//        submission.setDocuments(documents);

        /////
        submission.setAuthor(user);
        //////

        submissionRepository.save(submission);
        documentRepository.save(document);


        SubmissionUserRoles userRoles = new SubmissionUserRoles();
        SubmissionRole author = roleRepository.findByName(SubmissionRoleName.AUTHOR);
        userRoles.setUser(user);
        userRoles.setRole(author);
        userRoles.setSubmission(submission);
        submissionUserRolesRepository.save(userRoles);


        logger.debug("SubmissionService: after save()");
        logger.debug("SubmissionService: userRoles.getUser(): " + userRoles.getUser());
    }

    @Override
    public List<SubmissionDto> getAll(boolean reviewable) {
        List<Submission> submissions = submissionRepository.findAll();
        if (reviewable) {
            submissions = submissions.stream().filter(Submission::isReviewable).collect(Collectors.toList());
        }
        return SubmissionMapper.toListDto(submissions);
    }

    @Override
    public SubmissionDto getSubmission(Long id) {
        Submission submission = submissionRepository.getOne(id);
        return SubmissionMapper.toDto(submission);

    }

    @Override
    public SubmissionDto setOnReview(Long submissionId, Boolean reviewable) {
        logger.debug("IN SET ON REVIEW");
        Submission submission = submissionRepository.getOne(submissionId);
        submission.setReviewable(reviewable);

        submission = submissionRepository.save(submission);
        return SubmissionMapper.toDto(submission);
    }

    @Override
    public void addReviewers(Long submissionId, List<Long> reviewers) {
        logger.info("ADDREVIEWERS");
        List<User> userList = reviewers.stream().map(userRepository::getOne).collect(Collectors.toList());
        Submission submission = submissionRepository.getOne(submissionId);
        SubmissionRole reviewerRole = submissionRoleRepository.findByName(SubmissionRoleName.REVIEWER);

        // Fetch users from submission conference
        List<User> conferenceUserList = new ArrayList<>();
        submission.getConference().getConferenceUserRoles().forEach(conferenceUserRoles -> conferenceUserList.add(conferenceUserRoles.getUser()));

        outer:
        for (User user : userList) {
            // Check if user is not in the conference
            if (!conferenceUserList.contains(user))
                continue outer;
            // Check if user already has role
            // Author can't be the reviewer
            for (SubmissionUserRoles userRoles : submission.getSubmissionUserRoles()) {
                if (userRoles.getUser().equals(user))
                    continue outer;
            }
            // Creating new relation between User-Submission-Role
            SubmissionUserRoles submissionUserRoles = new SubmissionUserRoles();
            submissionUserRoles.setSubmission(submission);
            submissionUserRoles.setRole(reviewerRole);
            submissionUserRoles.setUser(user);
            submissionUserRolesRepository.save(submissionUserRoles);
        }
    }

    @Override
    public SubmissionDto uploadDocument(Long submissionId, MultipartFile file) throws IOException {
        Submission submission = submissionRepository.getOne(submissionId);
        Document document = new Document();
        document.setFilename(file.getOriginalFilename());
        document.setData(file.getBytes());
        document.setSubmission(submission);
        document.setStatus(submissionStatusRepository.findByName(SubmissionStatusName.PENDING));
        documentRepository.save(document);
        submission.setReviewable(false); ///////
        submission.setStatus(submissionStatusRepository.findByName(SubmissionStatusName.PENDING));
        return SubmissionMapper.toDto(submissionRepository.save(submission));
    }

    @Override
    public void deleteReviewers(Long submissionId, List<Long> reviewers) {
        List<User> userList = reviewers.stream().map(userRepository::getOne).collect(Collectors.toList());
        Submission submission = submissionRepository.getOne(submissionId);
        SubmissionRole reviewerRole = submissionRoleRepository.findByName(SubmissionRoleName.REVIEWER);

        for (User user : userList) {
            for (SubmissionUserRoles userRoles : submission.getSubmissionUserRoles()) {
                // if users exists and has reviewer role
                if (userRoles.getUser().equals(user) && userRoles.getRole().equals(reviewerRole)) {
                    logger.info("in the if");
                    submissionUserRolesRepository.delete(userRoles);
                }
            }
        }
    }

    @Override
    public List<DocumentDto> getDocuments(Long submissionId) {
        Submission submission = submissionRepository.getOne(submissionId);
        return DocumentMapper.toListDto(submission.getDocuments());
    }

    @Override
    public Page<BriefSubmissionDto> findSubmissionsByConferenceId(Long conferenceId, Pageable pageable) {
        return submissionRepository.findAllByConferenceId(conferenceId, pageable).map(SubmissionMapper::toBriefDto);
    }

    @Override
    public Page<BriefSubmissionDto> getUserSubmissionsByConferenceId(Long conferenceId, Long userId, Pageable pageable) {
        return submissionRepository
                .findAllByConferenceIdAndUserIdAndRoleName(conferenceId, userId, SubmissionRoleName.AUTHOR, pageable)
                .map(SubmissionMapper::toBriefDto);
    }

    @Override
    public Page<BriefSubmissionDto> getReviewerSubmissionsByConferenceId(Long conferenceId, Long userId, Pageable pageable) {
        return submissionRepository
                .findAllByConferenceIdAndUserIdAndRoleName(conferenceId, userId, SubmissionRoleName.REVIEWER, pageable)
                .map(SubmissionMapper::toBriefDto);
    }

    @Override
    public Page<BriefSubmissionDto> findSubmissionsByConferenceIdAndStatus(Long conferenceId, Integer statusNumber, Pageable pageable) {
        SubmissionStatus submissionStatus = submissionStatusRepository.findByName(SubmissionStatusName.fromInt(statusNumber));
        return submissionRepository
                .findAllByConferenceIdAndStatus(conferenceId, submissionStatus, pageable)
                .map(SubmissionMapper::toBriefDto);
    }

    @Override
    public Page<BriefSubmissionDto> getUserSubmissionsByConferenceIdAndStatus(Long conferenceId, Long userId, Integer statusNumber, Pageable pageable) {
        SubmissionStatus submissionStatus = submissionStatusRepository.findByName(SubmissionStatusName.fromInt(statusNumber));
        return submissionRepository
                .findAllByConferenceIdAndUserIdAndRoleNameAndStatus(conferenceId, userId, SubmissionRoleName.AUTHOR, submissionStatus, pageable)
                .map(SubmissionMapper::toBriefDto);
    }

    @Override
    public Page<BriefSubmissionDto> getReviewerSubmissionsByConferenceIdAndStatus(Long conferenceId, Long reviewerId, Integer statusNumber, Pageable pageable) {
        SubmissionStatus submissionStatus = submissionStatusRepository.findByName(SubmissionStatusName.fromInt(statusNumber));
        return submissionRepository
                .findAllByConferenceIdAndUserIdAndRoleNameAndStatus(conferenceId, reviewerId, SubmissionRoleName.REVIEWER, submissionStatus, pageable)
                .map(SubmissionMapper::toBriefDto);

    }

//    private static String rusToEngTranlit(String text) {
//        char[] abcCyr = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', ' '};
//        String[] abcLat = {"a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "i", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "kh", "ts", "ch", "sh", "sch", "", "y", "", "e", "yu", "ya", "_"};
//
//        StringBuilder builder = new StringBuilder();
//        text = text.toLowerCase();
//        for (int i = 0; i < text.length(); i++) {
//            for (int x = 0; x < abcCyr.length; x++)
//                if (text.charAt(i) == abcCyr[x]) {
//                    logger.debug("char: " + text.charAt(i));
//                    builder.append(abcLat[x]);
//                }
//        }
//        return builder.toString();
//    }


}

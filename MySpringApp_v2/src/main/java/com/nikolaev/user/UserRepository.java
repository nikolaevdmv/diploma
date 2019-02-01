package com.nikolaev.user;

import com.nikolaev.conference.Conference;
import com.nikolaev.conference_role.ConferenceRoleName;
import com.nikolaev.conference_user_roles.ConferenceUserRoles;
import com.nikolaev.submission_role.SubmissionRoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByConfirmationToken(String confirmationToken);

    User getOne(Long id);

    List<User> findByAuthorities(List authorities);

    @Query("select u from User u join u.conferenceUserRoles userRoles " +
            "join userRoles.roleList roleList " +
            "join roleList.roles roles where userRoles.conference.id = ?1 and roles.name = ?2 group by u")
    Page<User> findAllByConferenceIdAndRoleName(Long conferenceId, ConferenceRoleName roleName, Pageable pageable);

    @Query("select u from User u join u.conferenceUserRoles userRoles where userRoles.conference.id = ?1")
    Page<User> findAllByConferenceId(Long conferenceId, Pageable pageable);

    @Query("select u from User u join u.submissionUserRoles userRoles where userRoles.submission.id = ?1 and " +
            "userRoles.role.name = ?2")
    Page<User> findAllBySubmissionIdAndRoleName(Long submissionId, SubmissionRoleName roleName, Pageable pageable);

//    @Query("select u from User u join u.conferenceUserRoles userRoles " +
//            "join userRoles.roleList roleList " +
//            "join roleList.roles roles where userRoles.conference.id = ?1 and not roles.name = ?2 and u.username like ?3% group by u")
//    Page<User> findAllNonReviewersByConferenceId(Long id, ConferenceRoleName reviewer, String searchString, Pageable pageable);

    @Query("select u from User u join u.conferenceUserRoles userRoles " +
            "join userRoles.roleList roleList " +
            "join roleList.roles roles where userRoles.conference.id = ?1 and roles.name = ?2 and u.username like ?3% group by u")
    Page<User> searchByConferenceIdAndRoleName(Long conferenceId, ConferenceRoleName roleName, String searchString, Pageable pageable);

    @Override
    Page<User> findAll(Pageable pageable);

    @Query("select u from User u where u.username like ?1%")
    Page<User> searchAll(String searchString, Pageable pageable);
}

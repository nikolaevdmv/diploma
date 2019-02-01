package com.nikolaev.submission;

import com.nikolaev.submission.status.SubmissionStatus;
import com.nikolaev.submission_role.SubmissionRoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Submission getOne(Long id);

    Page<Submission> findAllByConferenceId(Long conferenceId, Pageable pageable);

    Page<Submission> findAllByConferenceIdAndStatus(Long conferenceId, SubmissionStatus status, Pageable pageable);


    @Query("select s from Submission s join s.submissionUserRoles userRoles " +
            "join userRoles.role role where s.conference.id = ?1 and userRoles.user.id = ?2 and role.name = ?3")
    Page<Submission> findAllByConferenceIdAndUserIdAndRoleName
            (Long conferenceId, Long authorId, SubmissionRoleName roleName, Pageable pageable);


    @Query("select s from Submission s join s.submissionUserRoles userRoles " +
            "join userRoles.role role where s.conference.id = ?1 and userRoles.user.id = ?2 and role.name = ?3 and s.status = ?4")
    Page<Submission> findAllByConferenceIdAndUserIdAndRoleNameAndStatus
            (Long conferenceId, Long authorId, SubmissionRoleName roleName, SubmissionStatus status, Pageable pageable);


}


//    Page<Submission> findAllByConferenceIdAndAuthorId(Long conferenceId, Long authorId, Pageable pageable);
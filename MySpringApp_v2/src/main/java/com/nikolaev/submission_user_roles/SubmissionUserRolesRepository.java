package com.nikolaev.submission_user_roles;

import com.nikolaev.submission.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionUserRolesRepository extends JpaRepository<SubmissionUserRoles, Long> {
    @Override
    SubmissionUserRoles getOne(Long id);

    SubmissionUserRoles findBySubmission(Submission submission);
}

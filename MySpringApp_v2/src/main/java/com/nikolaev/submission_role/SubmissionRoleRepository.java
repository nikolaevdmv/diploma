package com.nikolaev.submission_role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRoleRepository extends JpaRepository<SubmissionRole, Long> {
    SubmissionRole findByName(SubmissionRoleName name);
}

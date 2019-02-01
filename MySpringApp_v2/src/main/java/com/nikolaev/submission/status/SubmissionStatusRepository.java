package com.nikolaev.submission.status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionStatusRepository extends JpaRepository<SubmissionStatus, Long> {
    SubmissionStatus findByName(SubmissionStatusName name);
}

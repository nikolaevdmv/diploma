package com.nikolaev.conference_request.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRequestCommentRepository extends JpaRepository<ConferenceRequestComment, Long> {
}

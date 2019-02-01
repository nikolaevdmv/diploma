package com.nikolaev.conference_request.status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRequestStatusRepository extends JpaRepository<ConferenceRequestStatus, Long> {
    ConferenceRequestStatus findByName(ConferenceRequestStatusName name);
}

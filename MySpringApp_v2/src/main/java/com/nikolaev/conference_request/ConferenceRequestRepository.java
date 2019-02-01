package com.nikolaev.conference_request;

import com.nikolaev.conference_request.status.ConferenceRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConferenceRequestRepository extends JpaRepository<ConferenceRequest, Long> {
    ConferenceRequest getOne(Long id);

    @Override
    Page<ConferenceRequest> findAll(Pageable pageable);

    Page<ConferenceRequest> findAllByStatus(ConferenceRequestStatus status, Pageable pageable);

    Page<ConferenceRequest> findAllByOrganizerId(Long organizerId, Pageable pageable);

    Page<ConferenceRequest> findAllByOrganizerIdAndStatus(Long organizerId, ConferenceRequestStatus status, Pageable pageable);
}

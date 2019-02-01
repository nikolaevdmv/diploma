package com.nikolaev.conference_user_roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceUserRolesRepository extends JpaRepository<ConferenceUserRoles, Long> {
    ConferenceUserRoles findByConferenceId(Long conferenceId);
    ConferenceUserRoles findByConferenceIdAndUserId(Long conferenceId, Long userId);
}

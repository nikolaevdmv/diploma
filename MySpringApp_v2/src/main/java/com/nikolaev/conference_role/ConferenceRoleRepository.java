package com.nikolaev.conference_role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRoleRepository extends JpaRepository<ConferenceRole, Long> {
    ConferenceRole findByName(ConferenceRoleName name);
}

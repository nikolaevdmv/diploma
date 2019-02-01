package com.nikolaev.conference;

import com.nikolaev.conference.Conference;
import com.nikolaev.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface ConferenceRepository extends JpaRepository<Conference,Long> {
//    @Query("select c from CONFERENCE c join  ")
//    List<Conference> findAllByUsername(String username);


    @Override
    Page<Conference> findAll(Pageable pageable);



    Conference getOne(Long id);

    @Query("select c from Conference c where c.expirationDate > ?1")
    Page<Conference> findAllActive(Date today, Pageable pageable);

    @Query("select c from Conference c where c.expirationDate < ?1")
    Page<Conference> findAllCompleted(Date today, Pageable pageable);
}

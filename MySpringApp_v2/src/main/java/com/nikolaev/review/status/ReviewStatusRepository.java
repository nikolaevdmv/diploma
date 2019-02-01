package com.nikolaev.review.status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewStatusRepository extends JpaRepository<ReviewStatus, Long> {
    ReviewStatus findByName(ReviewStatusName name);
}

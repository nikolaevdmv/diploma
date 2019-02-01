package com.nikolaev.review;

import com.nikolaev.review.dto.ReviewDto;

public interface ReviewService {
    ReviewDto getReview(Long id);

    ReviewDto update(ReviewDto reviewDto);

    ReviewDto submitReview(Long reviewId);

    void deleteReview(Long reviewId);

    void createReview(Long documentId, ReviewDto reviewDto);
}

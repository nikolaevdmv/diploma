package com.nikolaev.review.dto;

import com.nikolaev.review.Review;
import com.nikolaev.review.status.ReviewStatus;
import com.nikolaev.user.dto.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewMapper {


    private ReviewMapper() {
    }

    public static ReviewDto toDto(Review entity) {
        return new ReviewDto(
                entity.getId(),
                entity.getTitle(),
                entity.isSubmitted(),
                entity.getEvaluation(),
                entity.getDate(),
                mapStatus(entity.getStatus()),
                UserMapper.toBriefDto(entity.getAuthor())
        );
    }

    public static BriefReviewDto toBriefDto(Review entity) {
        return new BriefReviewDto(
                entity.getId(),
                entity.getTitle(),
                mapStatus(entity.getStatus()),
                entity.isSubmitted(),
                UserMapper.toBriefDto(entity.getAuthor())
        );
    }

    public static List<BriefReviewDto> toListBriefDto(List<Review> reviews) {
        return reviews.stream().map(ReviewMapper::toBriefDto).collect(Collectors.toList());
    }

    public static List<ReviewDto> toListDto(List<Review> reviews) {
        return reviews.stream().map(ReviewMapper::toDto).collect(Collectors.toList());
    }

    private static int mapStatus(ReviewStatus status) {
        return status.getName().getValue();
    }
}

package com.nikolaev.review;


import com.nikolaev.review.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "{reviewId}", method = RequestMethod.GET)
    public ResponseEntity getReview(@PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.ok(this.reviewService.getReview(reviewId));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(this.reviewService.update(reviewDto));
    }

    @RequestMapping(value = "{reviewId}/submit", method = RequestMethod.POST)
    public ResponseEntity submitReview(@PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.ok(this.reviewService.submitReview(reviewId));
    }

    @RequestMapping(value = "{reviewId}", method = RequestMethod.DELETE)
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        this.reviewService.deleteReview(reviewId);
    }
}

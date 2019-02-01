package com.nikolaev.review.status;

import com.nikolaev.review.Review;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "review_status")
public class ReviewStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ReviewStatusName name;

    @OneToMany(mappedBy = "status")
    private List<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReviewStatusName getName() {
        return name;
    }

    public void setName(ReviewStatusName name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}

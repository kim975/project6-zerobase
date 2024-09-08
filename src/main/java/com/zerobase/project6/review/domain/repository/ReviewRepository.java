package com.zerobase.project6.review.domain.repository;

import com.zerobase.project6.review.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByReviewToken(String reviewToken);

}

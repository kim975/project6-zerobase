package com.zerobase.project6.review.domain.repository;

import com.zerobase.project6.review.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

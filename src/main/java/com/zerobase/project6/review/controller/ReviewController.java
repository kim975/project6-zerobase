package com.zerobase.project6.review.controller;

import com.zerobase.project6.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@PreAuthorize("hasAnyRole('STORE_OWNER', 'CUSTOMER')")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> registerReview(
            Authentication authentication,
            @RequestBody ReviewDto.RegisterReview request
    ) {
        return ResponseEntity.ok(reviewService.registerReview(request.toCommand(authentication.getName())));
    }

}

package com.zerobase.project6.review.controller;

import com.zerobase.project6.review.service.ReviewService;
import com.zerobase.project6.user.domain.model.common.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@PreAuthorize("hasAnyRole('STORE_OWNER', 'CUSTOMER')")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 
     * 리뷰 등록 
     * 
     * @param authentication
     * @param request
     * @return
     */
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> registerReview(
            Authentication authentication,
            @RequestBody ReviewDto.RegisterReview request
    ) {
        return ResponseEntity.ok(reviewService.registerReview(request.toCommand(authentication.getName())));
    }

    /**
     * 
     * 리뷰 수정
     * 
     * @param authentication
     * @param request
     * @return
     */
    @PutMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> updateReview(
            Authentication authentication,
            @RequestBody ReviewDto.UpdateReview request
    ) {
        return ResponseEntity.ok(reviewService.updateReview(request.toCommand(authentication.getName())));
    }

    /**
     * 리뷰 삭제
     * @param authentication
     * @param reviewToken
     * @return
     */
    @DeleteMapping("/{reviewToken}")
    @PreAuthorize("hasAnyRole('STORE_OWNER', 'CUSTOMER')")
    public ResponseEntity<Void> deleteReview(
            Authentication authentication,
            @PathVariable String reviewToken
    ) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<? extends GrantedAuthority> list = authorities.stream().toList();
        GrantedAuthority grantedAuthority = list.get(0);

        reviewService.deleteReview(reviewToken, authentication.getName(), UserType.valueOf(grantedAuthority.getAuthority().replace("ROLE_", "")));

        return ResponseEntity.ok().build();
    }
}

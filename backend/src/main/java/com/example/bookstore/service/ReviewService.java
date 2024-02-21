package com.example.bookstore.service;

import com.example.bookstore.database.entity.Review;
import com.example.bookstore.database.repository.ReviewRepository;
import com.example.bookstore.dto.ReviewRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void postReview(
            String userEmail,
            ReviewRequestModel reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findTopByUserEmailAndProductId(
                userEmail, reviewRequest.getProductId());

        if (validateReview != null) {
            throw new Exception("Повторная попытка");
        }

        Review review = new Review();
        review.setProductId(reviewRequest.getProductId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);
        if (reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewText(
                    reviewRequest
                            .getReviewDescription()
                            .map(t -> t.toString())
                            .orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long productId) {
        return reviewRepository
                .findTopByUserEmailAndProductId(userEmail, productId) != null;
    }
}

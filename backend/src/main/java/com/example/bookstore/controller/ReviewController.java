package com.example.bookstore.controller;

import com.example.bookstore.dto.ReviewRequestModel;
import com.example.bookstore.service.ReviewService;
import com.example.bookstore.utils.JWTParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private String extractEmail(String token, String string) {
        String email = JWTParser.jwtExtraction(token, "\"sub\"");
        if (email != null)
            return email;
        return "";
    }

    @GetMapping("/secure/user/product")
    public Boolean reviewProductByUser(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam Long productId) throws Exception {
        String userEmail = extractEmail(token, "\"sub\"");
        if (userEmail == null) {
            throw new Exception("Ошибка пользователя");
        }
        return reviewService.userReviewListed(userEmail, productId);
    }

    @PostMapping("/secure")
    public void postReview(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody ReviewRequestModel reviewRequest) throws Exception {
        String userEmail = extractEmail(token, "\"sub\"");
        if (userEmail == null) {
            throw new Exception("Ошибка пользователя");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }
}

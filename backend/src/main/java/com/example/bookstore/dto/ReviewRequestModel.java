package com.example.bookstore.dto;


import lombok.Data;

import java.util.Optional;

@Data
public class ReviewRequestModel {
    private double rating;
    private Long productId;
    private Optional<String> reviewDescription;
}
